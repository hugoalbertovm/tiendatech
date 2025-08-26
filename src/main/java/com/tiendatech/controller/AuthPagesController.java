package com.tiendatech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthPagesController {

    @GetMapping("/cliente/login_cliente")
    public String loginCliente() {
        return "cliente/login_cliente";
    }

    @GetMapping("/admin/login_admin")
    public String loginAdmin() {
        return "admin/login_admin";
    }
}
