package org.consulta.controller;

import org.consulta.dao.ConsultaDAO;
import org.consulta.dao.MedicoDAO;
import org.consulta.dao.PacienteDAO;
import org.consulta.domain.Consulta;
import org.consulta.domain.Paciente;
import org.consulta.domain.Usuario;
import org.consulta.util.Erro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/pacientes/*")
public class PacienteController extends HttpServlet {

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
        } else if (!usuario.getCargo().equals("paciente")) {
            erros.add("Acesso não autorizado - Cargo incorreto");
            erros.add("Apenas pacientes tem acesso a essa página.");
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
                case "/agendamento":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
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
        String cpf = request.getParameter("cpf");
        List<Consulta> lista = consultaDao.getByCpf(cpf);
        request.setAttribute("listaConsultasPorPaciente", lista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/paciente/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/paciente/formulario.jsp");
        dispatcher.forward(request, response);
    }
    //Requisito R5
    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String cpf = request.getParameter("cpf_paciente");
        String crm = request.getParameter("crm_medico");
        String datetime = request.getParameter("data_hora");

        Consulta consulta = new Consulta(cpf, crm, datetime);
        consultaDao.insert(consulta);
        response.sendRedirect("lista");
    }
}