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
                    //verificarAutorizacao(request, response, "paciente");
                    listagemConsultas(request, response);
                    break;
                case "/criarConsulta":
                    //verificarAutorizacao(request, response, "paciente");
                    criarConsulta(request, response);
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

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacientes/formulario.jsp");
        dispatcher.forward(request, response);
    }
    //Requisito R5
    private void criarConsulta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Medico> listaMedicos = medicoDao.getAll();
        request.setAttribute("listaMedicos", listaMedicos);
        if (request.getMethod().equalsIgnoreCase("POST")) {
            String cpf = request.getParameter("cpf");
            String crm = request.getParameter("crm");
            String dataHora = request.getParameter("data_hora");

            System.out.print(cpf + crm + dataHora);

            boolean check = consultaDao.checkValidity(crm, dataHora, cpf);

            if (check) {
                Consulta consulta = new Consulta(cpf, crm, dataHora);
                consultaDao.insert(consulta);
                response.sendRedirect(request.getContextPath() + "/pacientes/listagemConsultas?doc=" + cpf);
            } else {
                request.setAttribute("errorMessage", "Uma consulta neste horário já existe");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacientes/criarConsulta.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacientes/criarConsulta.jsp");
            dispatcher.forward(request, response);
        }
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

            Paciente jaExiste = pacienteDao.getByCpf(cpf);
            if (jaExiste != null) {
                request.setAttribute("errorMessage", "Um paciente com esse CPF já existe");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/pacientes/criarPacientes.jsp");
                dispatcher.forward(request, response);
                return;
            }

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
            String ogcpf = request.getParameter("ogcpf");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String cpf = request.getParameter("cpf");
            String nome = request.getParameter("nome");
            String telefone = request.getParameter("telefone");
            String sexo = request.getParameter("sexo");
            String data_nascimento = request.getParameter("data_nascimento");

            Usuario usuario = usuarioDao.getByDocumento(ogcpf);

            if (usuario != null){
                Paciente paciente = new Paciente(id, email, senha, cpf, nome, telefone, sexo, data_nascimento);
                usuario = new Usuario(usuario.getId(), email, senha, "paciente", nome, cpf);

                pacienteDao.update(paciente);
                usuarioDao.update(usuario);

                response.sendRedirect(request.getContextPath() + "/pacientes/listagemPacientes");
            } else {
                request.setAttribute("errorMessage", "Usuário com o CPF dado não existe");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/medicos/editarPacientes.jsp");
                dispatcher.forward(request, response);
            }

        } else {
            Long id = Long.parseLong(request.getParameter("id"));
            String ogcpf = request.getParameter("ogcpf");

            Paciente paciente = pacienteDao.get(id);
            request.setAttribute("paciente", paciente);
            request.setAttribute("ogcpf", ogcpf);

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