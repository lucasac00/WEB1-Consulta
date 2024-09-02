package org.consulta.controller;

import org.consulta.domain.Consulta;
import org.consulta.domain.Medico;
import org.consulta.domain.Usuario;
import org.consulta.service.spec.IConsultaService;
import org.consulta.service.spec.IMedicoService;
// import org.consulta.util.Erro;
import org.consulta.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoService medicoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IConsultaService consultaService;


    @GetMapping("/listagemMedicos")
    public String listagemMedicos(Model model) {
        List<Medico> listaMedicos = medicoService.buscarTodos();
        model.addAttribute("listaMedicos", listaMedicos);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = null;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            usuario = usuarioService.buscarPorLogin(username);
        }
        model.addAttribute("usuario", usuario);
        return "nlogado/medicos/listagemMedicos";
    }

    @GetMapping("/listagemEspecialidades")
    public String listagemEspecialidades(Model model) {
        List<String> listaEspecialidades = medicoService.getEspecialidades();
        model.addAttribute("listagemEspecialidades", listaEspecialidades);
        return "nlogado/medicos/listagemEspecialidades";
    }

    @GetMapping("/criarMedicos")
    public String criarMedicosForm(Model model) {
        model.addAttribute("medico", new Medico());
        return "logado/medicos/criarMedicos";
    }

    @PostMapping("/criarMedicos")
    public String criarMedicos(@ModelAttribute("medico") Medico medico, RedirectAttributes redirectAttributes) {
        if (medicoService.buscarPorCrm(medico.getCrm()) != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Um médico com esse CRM já existe");
            return "redirect:/medicos/criarMedicos";
        }
        try {
            Usuario usuario = new Usuario();
            usuario.setUsername(medico.getEmail());
            usuario.setEmail(medico.getEmail());
            usuario.setPassword(medico.getPassword());
            usuario.setCpf(medico.getCrm());
            usuario.setName(medico.getName());
            usuario.setRole("ROLE_MEDICO");
            usuario.setEnabled(true);
            usuarioService.salvar(usuario);

            medicoService.salvar(medico);
            return "redirect:/medicos/listagemMedicos";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Informações inválidas");
            return "redirect:/medicos/criarMedicos";
        }

    }

    @GetMapping("/editarMedicos/{id}")

    public String editarMedicosForm(@PathVariable long id, Model model) {
        Medico medico = medicoService.buscarPorId(id);
        if (medico == null) {
            return "redirect:/medicos/listagemMedicos";
        }
        Usuario usuario = usuarioService.buscarPorDocumento(medico.getCrm());
        model.addAttribute("medico", medico);
        model.addAttribute("ogcrm", usuario);
        return "logado/medicos/editarMedicos";
    }

    @PostMapping("/editarMedicos")
    public String editarMedicos(Medico medico, Usuario usuario, RedirectAttributes redirectAttributes) {
        medicoService.salvar(medico);
        /*usuario.setUsername(medico.getEmail());
        usuario.setPassword(medico.getPassword());
        usuario.setName(medico.getName());
        usuario.setCpf(medico.getCrm());
        usuario.setEmail(medico.getEmail());
        usuario.setRole("ROLE_MEDICO");
        usuarioService.salvar(usuario);*/
        return "redirect:/medicos/listagemMedicos";
    }

    @GetMapping("/deletarMedicos/{id}")
    public String deletarMedicos(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Medico medico = medicoService.buscarPorId(id);
        System.out.println("asdasd");
        if (medico != null) {
            /*Usuario usuario = usuarioService.buscarPorDocumento(medico.getCrm());
            if (usuario != null) {
                usuarioService.excluir(usuario.getId());
            }*/
            medicoService.excluir(medico.getId());
            redirectAttributes.addFlashAttribute("successMessage", "Médico deletado com sucesso.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Médico não encontrado.");
        }
        return "redirect:/medicos/listagemMedicos";
    }


    @GetMapping("/especialidade")
    public String listarMedicosPorEspecialidade(@RequestParam("nome") String especialidade, Model model) {
        List<Medico> listaMedicos = medicoService.buscarPorEspecialidade(especialidade);
        model.addAttribute("listaMedicos", listaMedicos);
        model.addAttribute("especialidade", especialidade);
        return "nlogado/medicos/listagemMedicosPorEspecialidade";
    }

    @GetMapping("/listagemConsultas")
    public String listagemConsultas(@RequestParam("crm") String crm, Model model) {
        List<Consulta> listaConsultas = consultaService.buscarPorCrm(crm);
        model.addAttribute("listaConsultas", listaConsultas);
        return "logado/medicos/listagemConsultas";
    }
}
