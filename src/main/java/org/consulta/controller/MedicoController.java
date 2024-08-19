package org.consulta.controller;

import org.consulta.domain.Medico;
import org.consulta.service.spec.IMedicoService;
// import org.consulta.util.Erro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoService medicoService;

    @GetMapping("/listagemMedicos")
    public String listagemMedicos(Model model) {
        List<Medico> listaMedicos = medicoService.buscarTodos();
        model.addAttribute("listaMedicos", listaMedicos);
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
        // Assuming there's a MedicoForm or similar class for binding form data
        model.addAttribute("medico", new Medico());
        return "logado/medicos/criarMedicos";
    }

    @PostMapping("/criarMedicos")
    public String criarMedicos(Medico medico, RedirectAttributes redirectAttributes) {
        if (medicoService.buscarPorCrm(medico.getCrm()) != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Um médico com esse CRM já existe");
            return "redirect:/medicos/criarMedicos";
        }
        medicoService.salvar(medico);
        return "redirect:/medicos/listagemMedicos";
    }

    @GetMapping("/editarMedicos/{id}")
    public String editarMedicosForm(@PathVariable Long id, Model model) {
        Medico medico = medicoService.buscarPorId(id);
        if (medico == null) {
            return "redirect:/medicos/listagemMedicos";
        }
        model.addAttribute("medico", medico);
        return "logado/medicos/editarMedicos";
    }

    @PostMapping("/editarMedicos")
    public String editarMedicos(Medico medico, RedirectAttributes redirectAttributes) {
        medicoService.atualizar(medico);
        return "redirect:/medicos/listagemMedicos";
    }

    @GetMapping("/deletarMedicos/{id}")
    public String deletarMedicos(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        medicoService.excluir(id);
        return "redirect:/medicos/listagemMedicos";
    }

    @GetMapping("/especialidade")
    public String listarMedicosPorEspecialidade(@RequestParam("nome") String especialidade, Model model) {
        List<Medico> listaMedicos = medicoService.buscarPorEspecialidade(especialidade);
        model.addAttribute("listaMedicos", listaMedicos);
        model.addAttribute("especialidade", especialidade);
        return "nlogado/medicos/listagemMedicosPorEspecialidade";
    }
}
