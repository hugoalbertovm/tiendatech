package com.tiendatech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginAdminController {

    @GetMapping("/admin/login_admin")
    public String mostrarLoginAdmin() {
        return "admin/login_admin";
    }
}
