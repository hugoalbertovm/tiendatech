package com.tiendatech.controller;

import com.tiendatech.dao.OfertaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OfertaClienteController {

    @Autowired
    private OfertaDao ofertaDao;

    @GetMapping("/ofertas")
    public String mostrarOfertas(Model model) {
        model.addAttribute("ofertas", ofertaDao.findAll());
        return "cliente/ofertas";
    }
}
