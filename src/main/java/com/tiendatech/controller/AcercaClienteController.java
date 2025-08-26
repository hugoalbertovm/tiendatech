package com.tiendatech.controller;

import com.tiendatech.service.AcercaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcercaClienteController {

    @Autowired
    private AcercaService acercaService;

    @GetMapping("/acerca")
    public String mostrarAcerca(Model model) {
        var lista = acercaService.listar();
        model.addAttribute("acerca", lista.isEmpty() ? null : lista.get(0));
        return "cliente/acerca";
    }
}
