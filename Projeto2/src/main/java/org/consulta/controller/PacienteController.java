package org.consulta.controller;

import org.consulta.domain.Paciente;
import org.consulta.domain.Usuario;
import org.consulta.service.spec.IPacienteService;
import org.consulta.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/listagemPacientes")
    public String listar(Model model) {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        model.addAttribute("listaPacientes", pacientes);
        return "logado/pacientes/listagemPacientes";
    }

    @GetMapping("/criarPacientes")
    public String criarPacientesForm(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "logado/pacientes/criarPacientes";
    }

    @PostMapping("/criarPacientes")
    public String criarPacientes(@ModelAttribute("paciente") Paciente paciente, RedirectAttributes redirectAttributes) {

        Paciente jaExiste = pacienteService.buscarPorCpf(paciente.getCpf());
        if (jaExiste != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Um paciente com esse CPF já existe");
            return "redirect:/pacientes/criarPacientes";
        }
        if(paciente.getCpf().length() > 64 || paciente.getEmail().length() > 64 || 
        paciente.getNome().length() > 64 || paciente.getSenha().length() > 64 || 
        paciente.getSexo().length() > 64 || paciente.getTelefone().length() > 64 || paciente.getUsername().length() > 64) {
            redirectAttributes.addFlashAttribute("errorMessage", "Você está com uma entrada longa demais");
            return "redirect:/pacientes/criarPacientes";
        }

        // Create a new Usuario and Paciente
        try {
            Usuario usuario = new Usuario();
            usuario.setUsername(paciente.getUsername());
            usuario.setEmail(paciente.getEmail());
            usuario.setPassword(paciente.getSenha());
            usuario.setCpf(paciente.getCpf());
            usuario.setName(paciente.getNome());
            usuario.setRole("ROLE_PACIENTE");
            usuario.setEnabled(true);
            usuarioService.salvar(usuario);
            pacienteService.salvar(paciente);
            return "redirect:/pacientes/listagemPacientes";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Informações inválidas");
            return "redirect:/pacientes/criarPacientes";
        }
    }

    @GetMapping("/editarPacientes/{id}")
    public String editarPacientesForm(@PathVariable("id") Long id, Model model) {
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente == null) {
            return "logado/pacientes/listagemPacientes";
        }
        model.addAttribute("paciente", paciente);
        // System.out.println("oisidfuuyasdgf");
        return "logado/pacientes/editarPacientes";
    }

    @PostMapping("/editarPacientes")
    public String editarPacientes(@ModelAttribute("paciente") Paciente paciente, RedirectAttributes redirectAttributes) {
        pacienteService.atualizar(paciente);
        Usuario usuario = usuarioService.buscarPorDocumento(paciente.getCpf());
        if (usuario != null) {
            usuario.setUsername(paciente.getUsername());
            usuario.setEmail(paciente.getEmail());
            usuario.setName(paciente.getNome());
            usuario.setPassword(paciente.getSenha());
            usuarioService.atualizar(usuario);
        }
        return "redirect:/pacientes/listagemPacientes";
    }

    @GetMapping("/deletarPacientes/{id}")
    public String deletarPacientes(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente != null) {
            Usuario usuario = usuarioService.buscarPorDocumento(paciente.getCpf());
            pacienteService.excluir(id);
            if (usuario != null) {
                usuarioService.excluir(usuario.getId());
            }
        }
        return "redirect:/pacientes/listagemPacientes";
    }
}
