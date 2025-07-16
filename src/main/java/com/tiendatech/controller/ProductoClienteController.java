package com.tiendatech.controller;

import com.tiendatech.dao.ProductoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoClienteController {

    @Autowired
    private ProductoDao productoDao;

    @GetMapping("/productos")
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", productoDao.findAll());
        return "cliente/productos";
    }
}
