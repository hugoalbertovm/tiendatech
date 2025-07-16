package com.tiendatech.controller;

import com.tiendatech.dao.OfertaDao;
import com.tiendatech.domain.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/ofertas")
public class OfertaAdminController {

    @Autowired
    private OfertaDao ofertaDao;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ofertas", ofertaDao.findAll());
        return "admin/ofertas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("oferta", new Oferta());
        return "admin/oferta_form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Oferta oferta) {
        ofertaDao.save(oferta);
        return "redirect:/admin/ofertas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("oferta", ofertaDao.findById(id).orElse(null));
        return "admin/oferta_form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        ofertaDao.deleteById(id);
        return "redirect:/admin/ofertas";
    }
}
