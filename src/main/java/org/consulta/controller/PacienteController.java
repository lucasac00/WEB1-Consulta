package org.consulta.controller;

import org.consulta.dao.ConsultaDAO;
import org.consulta.dao.MedicoDAO;
import org.consulta.dao.PacienteDAO;
import org.consulta.dao.UsuarioDAO;
import org.consulta.domain.Consulta;
import org.consulta.domain.Medico;
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
    private PacienteDAO pacienteDao;
    private UsuarioDAO usuarioDao;
    @Override
    public void init(){
        medicoDao = new MedicoDAO();
        consultaDao = new ConsultaDAO();
        pacienteDao = new PacienteDAO();
        usuarioDao = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null){
            action = "";
        }

        try {
            switch (action) {
                case "/criarPacientes":
                    verificarAutorizacao(request, response, "admin");
                    criarPacientes(request, response);
                    break;
                case "/editarPacientes":
                    verificarAutorizacao(request, response, "admin");
                    editarPacientes(request, response);
                    break;
                case "/deletarPacientes":
                    verificarAutorizacao(request, response, "admin");
                    deletarPacientes(request, response); 
                    break;
                case "/listagemPacientes":
                    verificarAutorizacao(request, response, "admin");
                    listagemPacientes(request, response);
                    break;
                case "/listagemConsultas":
                    //verificarAutorizacao(request, response, "admin");
                    listagemConsultas(request, response);
                    break;
                case "/criarConsulta":
                    apresentaFormCadastro(request, response);
                    break;
                case "/confirmarConsulta":
                    confirmarConsulta(request, response);
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

    private void verificarAutorizacao(HttpServletRequest request, HttpServletResponse response, String cargo) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();

        if (usuario == null) {
            response.sendRedirect(request.getContextPath());
            return;
        } else if (!usuario.getCargo().equals(cargo) && !usuario.getCargo().equals("admin")) {
            erros.add("Acesso não autorizado - Cargo incorreto");
            erros.add("Apenas admins têm acesso a essa página.");
            request.setAttribute("mensagens", erros);
            System.out.println(erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
            return;
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        List<Consulta> lista = consultaDao.getByCpf(cpf);
        request.setAttribute("listaConsultasPorPaciente", lista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacientes/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void listagemPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Paciente> listaPacientes = pacienteDao.getAll();
        request.setAttribute("listaPacientes", listaPacientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacientes/listagemPacientes.jsp");
        dispatcher.forward(request, response);
    }

    private void listagemConsultas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doc = request.getParameter("doc");
        List<Consulta> listaConsultas = consultaDao.getByCpf(doc);
        System.out.println(doc);
        System.out.println(listaConsultas.toString());
        request.setAttribute("listaConsultas", listaConsultas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacientes/listagemConsultas.jsp");
        dispatcher.forward(request, response);
    }

    //Requisito R5
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Medico> listaMedicos = medicoDao.getAll();
        request.setAttribute("listaMedicos", listaMedicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacientes/formulario.jsp");
        dispatcher.forward(request, response);
    }

    //Requisito R5
    private void confirmarConsulta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*if (request.getMethod().equalsIgnoreCase("POST")) {
            request.setCharacterEncoding("UTF-8");

            String cpf = request.getParameter("cpf_paciente");
            String crm = request.getParameter("crm_medico");
            String datetime = request.getParameter("data_hora");

            Consulta consulta = new Consulta(cpf, crm, datetime);

            // Lembrar de Fazer a confirmação de que nao teve conflito de horario aqui eu acho

            consultaDao.insert(consulta);
            response.sendRedirect("lista");
    
        } else {*/
            System.out.println("Chegou no confirmar Consulta");
            Long id = Long.parseLong(request.getParameter("id"));
            Medico medico = medicoDao.get(id);
            request.setAttribute("medico", medico);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacientes/confirmarConsulta.jsp");
            dispatcher.forward(request, response);
        //}
    }

    //Requisito R5 (coloquei esse insere no if(post) do confirmar consulta, n sei se ta certo)
    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String cpf = request.getParameter("cpf_paciente");
        String crm = request.getParameter("crm_medico");
        String datetime = request.getParameter("data_hora");

        Consulta consulta = new Consulta(cpf, crm, datetime);
        consultaDao.insert(consulta);
        response.sendRedirect("lista");
    }

    private void criarPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("POST")) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String cpf = request.getParameter("cpf");
            String nome = request.getParameter("nome");
            String telefone = request.getParameter("telefone");
            String sexo = request.getParameter("sexo");
            String data_nascimento = request.getParameter("data_nascimento");

            Usuario usuario = new Usuario(email, senha, "paciente", nome, cpf);
            Paciente paciente = new Paciente(email, senha, cpf, nome, telefone, sexo, data_nascimento);
            usuarioDao.insert(usuario);
            pacienteDao.insert(paciente);

            response.sendRedirect(request.getContextPath() + "/pacientes/listagemPacientes");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacientes/criarPacientes.jsp");
            dispatcher.forward(request, response);
        }
    }

    // R2
    private void editarPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("POST")) {
            Long id = Long.parseLong(request.getParameter("id"));
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String cpf = request.getParameter("cpf");
            String nome = request.getParameter("nome");
            String telefone = request.getParameter("telefone");
            String sexo = request.getParameter("sexo");
            String data_nascimento = request.getParameter("data_nascimento");

            Paciente paciente = new Paciente(id, email, senha, cpf, nome, telefone, sexo, data_nascimento);
            pacienteDao.update(paciente);
    
            response.sendRedirect(request.getContextPath() + "/pacientes/listagemPacientes");
        } else {
            Long id = Long.parseLong(request.getParameter("id"));
            Paciente paciente = pacienteDao.get(id);
            request.setAttribute("paciente", paciente);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacientes/editarPacientes.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deletarPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Paciente paciente = pacienteDao.get(id);
        pacienteDao.delete(paciente);
    
        response.sendRedirect(request.getContextPath() + "/pacientes/listagemPacientes");
    }

}