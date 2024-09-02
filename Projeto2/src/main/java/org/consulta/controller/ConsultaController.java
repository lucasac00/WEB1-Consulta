package org.consulta.controller;

import org.consulta.domain.Consulta;
import org.consulta.domain.Medico;
import org.consulta.domain.Usuario;
import org.consulta.service.spec.IConsultaService;
import org.consulta.service.spec.IMedicoService;
import org.consulta.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;
    @Autowired
    private IMedicoService medicoService;
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/listagemConsultas")
    public String listagemConsultas(Model model) {
        List<Consulta> listaConsultas = consultaService.buscarTodos();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Usuario usuario = usuarioService.buscarPorLogin(username);
            if (usuario != null) {
                System.out.println("Usuario found: " + usuario.getRole());
            } else {
                System.out.println("Usuario not found for username: " + username);
            }
            model.addAttribute("usuario", usuario);
            model.addAttribute("usuarioLogado", usuario);
        }

        model.addAttribute("listaConsultas", listaConsultas);
        return "logado/consultas/listagemConsultas";
    }

    @GetMapping("/criarConsulta/{cpf}")
    public String criarConsultaForm(@PathVariable("cpf") String cpf, Model model) {
        model.addAttribute("consulta", new Consulta());

        List<Medico> listaMedicos = medicoService.buscarTodos();
        model.addAttribute("listaMedicos", listaMedicos);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Usuario usuario = usuarioService.buscarPorLogin(username);
            if (usuario != null) {
                System.out.println("Usuario found: " + usuario.getRole());
            } else {
                System.out.println("Usuario not found for username: " + username);
            }
            model.addAttribute("usuario", usuario);
            model.addAttribute("usuarioLogado", usuario);
        }

        return "logado/consultas/criarConsulta";
    }

    @PostMapping("/criarConsulta")
    public String criarConsulta(Consulta consulta, RedirectAttributes redirectAttributes) {

        System.out.println(consulta.getCpf() + consulta.getCrm() + consulta.getDataHora());

       boolean isValid = consultaService.checkValidity(consulta.getCrm(), consulta.getCpf(), consulta.getDataHora());
        System.out.println("teste: " + isValid);

       if (isValid) {
            redirectAttributes.addFlashAttribute("errorMessage", "Consulta inválida: já existe uma consulta marcada para este horário.");
            return "redirect:/consultas/criarConsulta/" + consulta.getCpf();
       }
        
        consultaService.salvar(consulta);
        return "redirect:/index";
    }

    @GetMapping("/editarConsulta/{id}")
    public String editarConsultaForm(@PathVariable Long id, Model model) {
        Consulta consulta = consultaService.buscarPorId(id);
        if (consulta == null) {
            return "redirect:/consultas/listagemConsultas";
        }
        model.addAttribute("consulta", consulta);
        return "logado/consultas/editarConsulta";
    }

    @PostMapping("/editarConsulta")
    public String editarConsulta(Consulta consulta, RedirectAttributes redirectAttributes) {
        boolean isValid = consultaService.checkValidity(consulta.getCrm(), consulta.getCpf(), consulta.getDataHora());
        if (!isValid) {
            redirectAttributes.addFlashAttribute("errorMessage", "Consulta inválida: já existe uma consulta marcada para este horário.");
            return "redirect:/consultas/editarConsulta/" + consulta.getId();
        }
        consultaService.atualizar(consulta);

        return "redirect:/consultas/listagemConsultas";
    }

    @GetMapping("/deletarConsulta/{id}")
    public String deletarConsulta(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        consultaService.excluir(id);
        return "redirect:/consultas/listagemConsultas";
    }

    @GetMapping("/consultasPorCpf")
    public String listarConsultasPorCpf(@RequestParam("cpf") String cpf, Model model) {
        List<Consulta> listaConsultas = consultaService.buscarPorCpf(cpf);
        model.addAttribute("listaConsultas", listaConsultas);
        model.addAttribute("cpf", cpf);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Usuario usuario = usuarioService.buscarPorLogin(username);
            if (usuario != null) {
                System.out.println("Usuario found: " + usuario.getRole());
            } else {
                System.out.println("Usuario not found for username: " + username);
            }
            model.addAttribute("usuario", usuario);
            model.addAttribute("usuarioLogado", usuario);
        }

        return "logado/consultas/listagemConsultas";
    }

    @GetMapping("/consultasPorCrm/{crm}")
    public String listarConsultasPorCrm(@PathVariable("crm") String crm, Model model) {
        List<Consulta> listaConsultas = consultaService.buscarPorCrm(crm);
        model.addAttribute("listaConsultas", listaConsultas);
        model.addAttribute("crm", crm);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Usuario usuario = usuarioService.buscarPorLogin(username);
            if (usuario != null) {
                System.out.println("Usuario found: " + usuario.getRole());
            } else {
                System.out.println("Usuario not found for username: " + username);
            }
            model.addAttribute("usuario", usuario);
            model.addAttribute("usuarioLogado", usuario);
        }

        model.addAttribute("listaConsultas", listaConsultas);

        return "logado/consultas/listagemConsultas";
    }
}
