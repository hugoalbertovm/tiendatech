package com.tiendatech.controller;

import com.tiendatech.domain.Cliente;
import com.tiendatech.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/registro";
    }

    @PostMapping("/registro")
    public String registrarCliente(@ModelAttribute("cliente") Cliente cliente, Model model) {
        try {
            clienteService.guardar(cliente);
            model.addAttribute("exito", true);
            model.addAttribute("cliente", new Cliente());
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "cliente/registro";
    }

}
