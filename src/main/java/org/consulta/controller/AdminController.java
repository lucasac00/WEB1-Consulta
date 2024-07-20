package org.consulta.controller;

import org.consulta.dao.UsuarioDAO;
import org.consulta.domain.Usuario;
import org.consulta.util.Erro;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/usuarios/*")
public class AdminController extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDao;

    @Override
    public void init() {
        usuarioDao = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();

        if (usuario == null) {
            response.sendRedirect(request.getContextPath());
            return;
        } else if (!usuario.getCargo().equals("admin")) {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
            return;
        }

        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> listaUsuarios = usuarioDao.getAll();
        request.setAttribute("listaUsuarios", listaUsuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Usuario usuario = usuarioDao.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formulario.jsp");
        request.setAttribute("usuario", usuario);
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String cargo = request.getParameter("cargo");
        String nome = request.getParameter("nome");

        Usuario usuario = new Usuario(login, senha, cargo, nome);

        usuarioDao.insert(usuario);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String papel = request.getParameter("papel");

        Usuario usuario = new Usuario(id, nome, login, senha, papel);

        usuarioDao.update(usuario);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Usuario usuario = new Usuario(id);
        usuarioDao.delete(usuario);
        response.sendRedirect("lista");
    }
}
