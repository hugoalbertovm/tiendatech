package com.tiendatech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardAdminController {

    @GetMapping("/admin/dashboard")
    public String mostrarDashboard() {
        return "admin/dashboard";
    }
}
