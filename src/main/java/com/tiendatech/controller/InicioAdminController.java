package com.tiendatech.controller;

import com.tiendatech.domain.Inicio;
import com.tiendatech.service.InicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/inicio")
public class InicioAdminController {

    @Autowired
    private InicioService inicioService;

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Inicio inicio = inicioService.obtenerPorId(id).orElse(new Inicio());
        model.addAttribute("inicio", inicio);
        return "admin/inicio_form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Inicio inicio) {
        inicioService.guardar(inicio);
        return "redirect:/admin/inicio";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        inicioService.eliminar(id);
        return "redirect:/admin/inicio";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("inicio", new Inicio());
        return "admin/inicio_form";
    }

    @GetMapping
    public String listar(Model model) {
        Iterable<Inicio> listaInicio = inicioService.listar();
        model.addAttribute("inicio", listaInicio);
        return "admin/inicio";
    }
}
