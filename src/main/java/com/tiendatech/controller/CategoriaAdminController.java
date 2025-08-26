package com.tiendatech.controller;

import com.tiendatech.domain.Categoria;
import com.tiendatech.service.CategoriaService;
import jakarta.validation.Valid;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

@Controller
@RequestMapping("/admin/categorias")
public class CategoriaAdminController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listar(Model model, @RequestParam(required = false) String error) {
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("error", error);
        return "admin/categorias";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "admin/categoria_form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/categoria_form";
        }
        categoriaService.guardar(categoria);
        return "redirect:/admin/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.buscarPorId(id));
        return "admin/categoria_form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        try {
            categoriaService.eliminar(id);
            return "redirect:/admin/categorias";
        } catch (IllegalStateException e) {
            String errorMessage = UriUtils.encode(e.getMessage(), StandardCharsets.UTF_8);
            return "redirect:/admin/categorias?error=" + errorMessage;
        }
    }
}
