package org.consulta.controller;

import org.consulta.dao.MedicoDAO;
import org.consulta.dao.PacienteDAO;
import org.consulta.dao.UsuarioDAO;
import org.consulta.domain.Medico;
import org.consulta.domain.Paciente;
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
public class UsuarioController extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDao;
    private MedicoDAO medicoDao;
    private PacienteDAO pacienteDao;

    @Override
    public void init() {
        usuarioDao = new UsuarioDAO();
        medicoDao = new MedicoDAO();
        pacienteDao = new PacienteDAO();
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
                case "/insercao_paciente":
                    inserePaciente(request, response);
                    break;
                case "/insercao_medico":
                    insereMedico(request, response);
                    break;
                case "/atualizacao_paciente":
                    atualizePaciente(request, response);
                    break;
                case "/atualizacao_medico":
                    atualizeMedico(request, response);
                    break;
                case "/remocao_paciente":
                    removePaciente(request, response);
                    break;
                case "/remocao_medico":
                    removeMedico(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/usuarios/lista_usuarios.jsp");
        dispatcher.forward(request, response);
    }

    public void listaPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Paciente> listaPacientes = pacienteDao.getAll();
        request.setAttribute("listaPacientes", listaPacientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/usuarios/lista_pacientes.jsp");
        dispatcher.forward(request, response);
    }

    public void listaMedicos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Medico> listaMedicos = medicoDao.getAll();
        request.setAttribute("listaMedicos", listaMedicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/usuarios/lista_medicos.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/usuarios/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Usuario usuario = usuarioDao.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/usuarios/formulario.jsp");
        request.setAttribute("usuario", usuario);
        dispatcher.forward(request, response);
    }

    private void inserePaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String cargo = request.getParameter("cargo");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String data = request.getParameter("data_nascimento");

        Usuario usuario = new Usuario(login, senha, cargo, nome);
        Paciente paciente = new Paciente(email, senha, cpf, nome, telefone, sexo, data);

        usuarioDao.insert(usuario);
        pacienteDao.insert(paciente);
        response.sendRedirect("lista");
    }

    private void insereMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String cargo = request.getParameter("cargo");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String crm = request.getParameter("crm");
        String especialidade = request.getParameter("especialidade");

        Usuario usuario = new Usuario(login, senha, cargo, nome);
        Medico medico = new Medico(email, senha, crm, nome, especialidade);

        usuarioDao.insert(usuario);
        medicoDao.insert(medico);
        response.sendRedirect("lista");
    }

    private void atualizePaciente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String cargo = request.getParameter("cargo");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String data = request.getParameter("data_nascimento");

        Usuario usuario = new Usuario(id, nome, login, senha, cargo);
        Paciente paciente = new Paciente(id, email, senha, cpf, nome, telefone, sexo, data);
        usuarioDao.update(usuario);
        pacienteDao.update(paciente);
        response.sendRedirect("lista");
    }

    private void atualizeMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String cargo = request.getParameter("cargo");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String crm = request.getParameter("crm");
        String especialidade = request.getParameter("especialidade");

        Usuario usuario = new Usuario(login, senha, cargo, nome);
        Medico medico = new Medico(email, senha, crm, nome, especialidade);

        usuarioDao.update(usuario);
        medicoDao.update(medico);
        response.sendRedirect("lista");
    }

    private void removePaciente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Usuario usuario = new Usuario(id);
        Paciente paciente = new Paciente(id);
        usuarioDao.delete(usuario);
        pacienteDao.delete(paciente);
        response.sendRedirect("lista");
    }

    private void removeMedico(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Usuario usuario = new Usuario(id);
        Medico medico = new Medico(id);
        usuarioDao.delete(usuario);
        medicoDao.delete(medico);
        response.sendRedirect("lista");
    }
}