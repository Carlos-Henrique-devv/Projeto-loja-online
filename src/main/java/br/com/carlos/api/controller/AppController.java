package br.com.carlos.api.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String cadastro() {
        return "register";
    }

    @GetMapping("/signin")
    public String Login() {
        return "signin";
    }

    @GetMapping("/auth")
    public String loginPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/users";
        }
        return "auth";
    }

    @GetMapping("/error")
    public String user() {
        return "error";
    }
}


