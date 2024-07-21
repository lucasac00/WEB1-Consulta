package org.consulta.controller;

import org.consulta.dao.UsuarioDAO;
import org.consulta.domain.Usuario;
import org.consulta.util.Erro;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Index", urlPatterns = { "/login", "/logout" })
public class IndexController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Erro erros = new Erro();
        if (request.getParameter("bOK") != null) {
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            if (login == null || login.isEmpty()) {
                erros.add("Login não informado!");
            }
            if (senha == null || senha.isEmpty()) {
                erros.add("Senha não informada!");
            }
            if (!erros.existErros()) {
                UsuarioDAO dao = new UsuarioDAO();
                Usuario usuario = dao.getByLogin(login);
                if (usuario != null) {
                    if (usuario.getSenha().equalsIgnoreCase(senha)) {
                        request.getSession().setAttribute("usuarioLogado", usuario);
                        String contextPath = request.getContextPath().replace("/", "");
                        request.getSession().setAttribute("contextPath", contextPath);
                        if (usuario.getCargo().equals("admin")) {
                            response.sendRedirect("usuario/"); //ROTA DE ADMIN, REQUISITOS R1 e R2
                        } else if (usuario.getCargo().equals("paciente")){
                            response.sendRedirect("paciente/"); //ROTA DE PACIENTE, REQUISITOS R5 e R6
                        } else if (usuario.getCargo().equals("medico")){
                            response.sendRedirect("medico/"); //ROTA DE MEDICO, REQUISITOS R8
                        }
                        return;
                    } else {
                        erros.add("Senha inválida!");
                    }
                } else {
                    erros.add("Usuário não encontrado!");
                }
            }
        }
        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);

        //TODO: página inicial que mostra todos os médicos, e permite que o usuário faça login
        //Não há requisito de CRUD de usuário, apenas admin pode criar médicos e pacientes
        String URL = "/login.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}