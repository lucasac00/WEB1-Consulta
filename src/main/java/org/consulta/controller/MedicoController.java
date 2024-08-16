package org.consulta.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.consulta.domain.Medico;
import org.consulta.service.spec.IMedicoService;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoService service;

    /*@GetMapping("/cadastrar")
    public String cadastrar(Editora editora) {
        return "editora/cadastro";
    }*/

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("medicos",service.buscarTodos());
        return "nlogado/medicos/listagemMedicos";
    }
    /*
    @PostMapping("/salvar")
    public String salvar(@Valid Editora editora, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "editora/cadastro";
        }

        service.salvar(editora);
        attr.addFlashAttribute("sucess", "editora.create.sucess");
        return "redirect:/editoras/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("editora", service.buscarPorId(id));
        return "editora/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Editora editora, BindingResult result, RedirectAttributes attr) {

        // Apenas rejeita se o problema não for com o CNPJ (CNPJ campo read-only)

        if (result.getFieldErrorCount() > 1 || result.getFieldError("CNPJ") == null) {
            return "editora/cadastro";
        }

        service.salvar(editora);
        attr.addFlashAttribute("sucess", "editora.edit.sucess");
        return "redirect:/editoras/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
        if (service.editoraTemLivros(id)) {
            model.addAttribute("fail", "editora.delete.fail");
        } else {
            service.excluir(id);
            model.addAttribute("sucess", "editora.delete.sucess");
        }
        return listar(model);
    }*/
}


/*package org.consulta.controller;

import org.consulta.dao.ConsultaDAO;
import org.consulta.dao.MedicoDAO;
import org.consulta.dao.UsuarioDAO;
import org.consulta.domain.Consulta;
import org.consulta.domain.Paciente;
import org.consulta.domain.Usuario;
import org.consulta.domain.Medico;
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
    private UsuarioDAO usuarioDao;

    @Override
    public void init(){
        medicoDao = new MedicoDAO();
        consultaDao = new ConsultaDAO();
        usuarioDao = new UsuarioDAO();
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
                case "/criarMedicos":
                    verificarAutorizacao(request, response, "medico");
                    criarMedicos(request, response);
                    break;
                case "/editarMedicos":
                    verificarAutorizacao(request, response, "medico");
                    editarMedicos(request, response);
                    break;
                case "/deletarMedicos":
                    verificarAutorizacao(request, response, "medico");
                    deletarMedicos(request, response); 
                    break;
                case "/listagemMedicos":
                    listagemMedicos(request, response);
                    break;
                case "/listagemEspecialidades":
                    listagemEspecialidades(request, response);
                    break;
                case "/listagemConsultas":
                    verificarAutorizacao(request, response, "medico");
                    listagemConsultas(request, response);
                    break;
                case "/especialidade":
                    listarMedicosPorEspecialidade(request, response);
                    break;
                default:
                    System.out.println("HELLO");
                    listagemMedicos(request, response);
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
            erros.add("Apenas médicos têm acesso a essa página.");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
            return;
        }
    }

    // R3 
    private void listagemMedicos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Medico> listaMedicos = medicoDao.getAll();
        request.setAttribute("listaMedicos", listaMedicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/nlogado/medicos/listagemMedicos.jsp");
        dispatcher.forward(request, response);
    }
    // R4 - listar as diferentes especialidades existentes
    private void listagemEspecialidades(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> listaEspecialidadesDistintas = medicoDao.getEspecialidadesDistintas();
        request.setAttribute("listagemEspecialidades", listaEspecialidadesDistintas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/nlogado/medicos/listagemEspecialidades.jsp");
        dispatcher.forward(request, response);
    }
    // R4 - Listar os medicos com essa especialidade
    private void listarMedicosPorEspecialidade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String especialidade = request.getParameter("nome");
        List<Medico> listaMedicos = medicoDao.getByEspecialidade(especialidade);
        request.setAttribute("listaMedicos", listaMedicos);
        request.setAttribute("especialidade", especialidade);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/nlogado/medicos/listagemMedicosPorEspecialidade.jsp");
        dispatcher.forward(request, response);
    }

    // R8 - Listar todas as consultas de um medico com base no seu CRM
    private void listagemConsultas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doc = request.getParameter("doc");
        List<Consulta> listaConsultas = consultaDao.getByCrm(doc);
        System.out.println(doc);
        System.out.println(listaConsultas.toString());
        request.setAttribute("listaConsultas", listaConsultas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/medicos/listagemConsultas.jsp");
        dispatcher.forward(request, response);
    }

    // R1 - Criar
    private void criarMedicos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("POST")) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String crm = request.getParameter("crm");
            String nome = request.getParameter("nome");
            String especialidade = request.getParameter("especialidade");

            Medico jaExiste = medicoDao.getByCrm(crm);
            if (jaExiste != null) {
                request.setAttribute("errorMessage", "Um médico com esse CRM já existe");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/medicos/criarMedicos.jsp");
                dispatcher.forward(request, response);
                return;
            }

            Usuario usuario = new Usuario(email, senha, "medico", nome, crm);
            Medico medico = new Medico(email, senha, crm, nome, especialidade);
            usuarioDao.insert(usuario);
            medicoDao.insert(medico);

            response.sendRedirect(request.getContextPath() + "/medicos/listagemMedicos");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/medicos/criarMedicos.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void editarMedicos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("POST")) {
            Long id = Long.parseLong(request.getParameter("id"));
            String og_crm = request.getParameter("ogcrm");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String crm = request.getParameter("crm");
            String nome = request.getParameter("nome");
            String especialidade = request.getParameter("especialidade");

            Usuario usuario = usuarioDao.getByDocumento(og_crm);
          
            if (usuario != null) {
                Medico medico = new Medico(id, email, senha, crm, nome, especialidade);
                usuario = new Usuario(usuario.getId(), email, senha, "medico", nome, crm);

                medicoDao.update(medico);
                usuarioDao.update(usuario);

                response.sendRedirect(request.getContextPath() + "/medicos/listagemMedicos");
            } else {
                request.setAttribute("errorMessage", "Usuário com o CRM dado não foi encontrado");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/medicos/editarMedicos.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            Long id = Long.parseLong(request.getParameter("id"));
            String og_crm = request.getParameter("ogcrm");

            Medico medico = medicoDao.get(id);
            request.setAttribute("medico", medico);
            request.setAttribute("ogcrm", og_crm);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/medicos/editarMedicos.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deletarMedicos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Medico medico = medicoDao.get(id);
        Usuario usuario = usuarioDao.getByDocumento(medico.getCrm());
        medicoDao.delete(medico);
        usuarioDao.delete(usuario);
    
        response.sendRedirect(request.getContextPath() + "/medicos/listagemMedicos");
    }

    
}*/