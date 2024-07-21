package org.consulta.controller;

import com.sun.net.httpserver.Request;
import org.consulta.dao.ConsultaDAO;
import org.consulta.dao.MedicoDAO;
import org.consulta.domain.Consulta;
import org.consulta.domain.Medico;
import org.consulta.domain.Usuario;
import org.consulta.util.Erro;

import java.io.IOException;
import java.util.ArrayList;
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
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/listagemMedicos":
                    listagemMedicos(request, response);
                    break;
                default:
                    verificarAutorizacao(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void verificarAutorizacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();

        if (usuario == null) {
            response.sendRedirect(request.getContextPath());
            return;
        } else if (!usuario.getCargo().equals("medico") && !usuario.getCargo().equals("admin")) {
            erros.add("Acesso não autorizado - Cargo incorreto");
            erros.add("Apenas médicos têm acesso a essa página.");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
            return;
        }

        lista(request, response);
    }


    //Requisito R8
    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String crm = request.getParameter("crm");
        List<Consulta> lista = consultaDao.getByCrm(crm);
        request.setAttribute("listaConsultasPorMedico", lista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/medico/lista.jsp");
        dispatcher.forward(request, response);
    }


    private void listagemMedicos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Medico> listaMedicos = medicoDao.getAll();
        request.setAttribute("listaMedicos", listaMedicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/nlogado/medicos/listagemMedicos.jsp");
        dispatcher.forward(request, response);
    }
    
}