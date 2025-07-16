package com.tiendatech.controller;

import com.tiendatech.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoriaClienteController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categorias")
    public String mostrarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listar());
        return "cliente/categorias";
    }
}
