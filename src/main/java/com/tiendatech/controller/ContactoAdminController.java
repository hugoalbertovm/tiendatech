package com.tiendatech.controller;

import com.tiendatech.domain.Contacto;
import com.tiendatech.service.ContactoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/contacto")
public class ContactoAdminController {

    private final ContactoService contactoService;

    public ContactoAdminController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Page<Contacto> pagina = contactoService.listar(PageRequest.of(page, size));
        model.addAttribute("pagina", pagina);
        return "admin/contacto";
    }

    @GetMapping("/editar/{id}")
    public String ver(@PathVariable Long id, Model model) {
        model.addAttribute("mensaje", contactoService.buscarPorId(id));
        model.addAttribute("estados", Contacto.Estado.values());
        return "admin/contacto_form";
    }

    @PostMapping("/{id}/estado")
    public String cambiarEstado(@PathVariable Long id, @RequestParam Contacto.Estado estado) {
        contactoService.cambiarEstado(id, estado);
        return "redirect:/admin/contacto/editar/" + id;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        contactoService.eliminar(id);
        return "redirect:/admin/contacto";
    }
}
