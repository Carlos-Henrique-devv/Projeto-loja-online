package br.com.carlos.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @GetMapping("/signin")
    public String Login() {
        return "signin";
    }

    @GetMapping("/auth")
    public String auth(HttpServletRequest request) {
        return "auth";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}


