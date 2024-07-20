package org.consulta.controller;

import com.sun.net.httpserver.Request;
import org.consulta.dao.ConsultaDAO;
import org.consulta.dao.MedicoDAO;
import org.consulta.domain.Consulta;
import org.consulta.domain.Medico;
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

@WebServlet(urlPatterns = "/medicos/*")
public class MedicoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MedicoDAO medicoDao;
    private ConsultaDAO consultaDao;

    @Override
    public void init(){
        medicoDao = new MedicoDAO();
        consultaDao = new ConsultaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();

        if (usuario == null){
            response.sendRedirect(request.getContextPath());
            return;
        } else if (!usuario.getCargo().equals("medico")) {
            erros.add("Acesso não autorizado - Cargo incorreto");
            erros.add("Apenas médicos tem acesso a essa página.");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
            return;
        }

        String action = request.getPathInfo();
        if (action == null){
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
                    atualiza(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }

    }

    /*//Requisito R3
    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Medico> lista = dao.getAll();
        request.setAttribute("listaMedicos", lista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lista_medicos.jsp");
        dispatcher.forward(request, response);
    }
    //Requisito R4
    private void listaPorEspecialidades(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String especialidade = request.getParameter("especialidade");
        List<Medico> lista = dao.getByEspecialidade(especialidade);
        request.setAttribute("listaMedicos", lista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lista_especialidades.jsp");
        dispatcher.forward(request, response);
    }*/
    //Requisito R8
    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String crm = request.getParameter("crm");
        List<Consulta> lista = consultaDao.getByCrm(crm);
        request.setAttribute("listaConsultasPorMedico", lista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/medico/lista.jsp");
        dispatcher.forward(request, response);
    }
    //Requisito R1
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/medico/formulario.jsp");
        dispatcher.forward(request, response);
    }
    //Requisito R1
    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Medico medico = medicoDao.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/medico/formulario.jsp");
        request.setAttribute("medico", medico);
        dispatcher.forward(request, response);
    }
    //Requisito R1
    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String crm = request.getParameter("crm");
        String nome = request.getParameter("nome");
        String especialidade = request.getParameter("especialidade");

        Medico medico = new Medico(email, senha, crm, nome, especialidade);

        medicoDao.insert(medico);
        response.sendRedirect("lista");
    }
    //Requisito R1
    private void atualiza(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String crm = request.getParameter("crm");
        String nome = request.getParameter("nome");
        String especialidade = request.getParameter("especialidade");

        Medico medico = new Medico(email, senha, crm, nome, especialidade);

        medicoDao.update(medico);
        response.sendRedirect("lista");
    }
    //Requisito R1
    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Medico medico = new Medico(id);
        medicoDao.delete(medico);
        response.sendRedirect("lista");
    }
}
