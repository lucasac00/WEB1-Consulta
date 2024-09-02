package org.consulta.controller;

import org.consulta.domain.Usuario;
import org.consulta.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login"; // maps to login.html
    }

    @GetMapping("/index")
    public String index(Model model) {
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
        return "index"; 
    }

}
