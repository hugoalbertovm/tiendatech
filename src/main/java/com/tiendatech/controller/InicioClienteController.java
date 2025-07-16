package com.tiendatech.controller;

import com.tiendatech.domain.Inicio;
import com.tiendatech.service.InicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioClienteController {

    @Autowired
    private InicioService inicioService;

    @GetMapping({"/", "/index"})
    public String mostrarInicioCliente(Model model) {

        Inicio inicio = inicioService.obtenerPrimerRegistro().orElse(null);

        if (inicio == null) {
            model.addAttribute("error", "No se ha encontrado el registro de inicio.");
            return "error";
        }

        model.addAttribute("inicio", inicio);
        return "cliente/index";
    }
}
