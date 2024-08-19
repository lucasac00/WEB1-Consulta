package org.consulta.controller;

import org.consulta.domain.Paciente;
import org.consulta.service.spec.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        model.addAttribute("pacientes", pacientes);
        return "admin/paciente/listagemPacientes";
    }

    @GetMapping("/cadastrar")
    public String cadastrarForm(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "admin/paciente/cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@ModelAttribute("paciente") Paciente paciente) {
        pacienteService.salvar(paciente);
        return "redirect:/pacientes/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") Long id, Model model) {
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente == null) {
            return "redirect:/pacientes/listar";
        }
        model.addAttribute("paciente", paciente);
        return "admin/paciente/editar";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("paciente") Paciente paciente) {
        pacienteService.atualizar(paciente);
        return "redirect:/pacientes/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") Long id) {
        pacienteService.excluir(id);
        return "redirect:/pacientes/listar";
    }
}
