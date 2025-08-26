package com.tiendatech.controller;

import com.tiendatech.domain.Acerca;
import com.tiendatech.service.AcercaService;
import jakarta.validation.Valid;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

@Controller
@RequestMapping("/admin/acerca")
public class AcercaAdminController {

    @Autowired
    private AcercaService acercaService;

    @GetMapping
    public String listar(Model model, @RequestParam(required = false) String error) {
        model.addAttribute("acercas", acercaService.listar());
        model.addAttribute("error", error);
        return "admin/acerca";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("acerca", new Acerca());
        return "admin/acerca_form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Acerca acerca, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/acerca_form";
        }
        acercaService.guardar(acerca);
        return "redirect:/admin/acerca";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("acerca", acercaService.buscarPorId(id));
        return "admin/acerca_form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        try {
            acercaService.eliminar(id);
            return "redirect:/admin/acerca";
        } catch (IllegalStateException e) {
            String msg = UriUtils.encode(e.getMessage(), StandardCharsets.UTF_8);
            return "redirect:/admin/acerca?error=" + msg;
        }
    }
}
