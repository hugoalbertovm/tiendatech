package com.tiendatech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginClienteController {

    @GetMapping("/login")
    public String mostrarLoginCliente() {
        return "cliente/login_cliente";
    }
}
