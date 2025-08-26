package com.tiendatech.controller;

import com.tiendatech.dao.ContactoForm;
import com.tiendatech.service.ContactoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contacto")
public class ContactoClienteController {

    private final ContactoService contactoService;

    public ContactoClienteController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @GetMapping
    public String verFormulario(Model model) {
        if (!model.containsAttribute("contacto")) {
            model.addAttribute("contacto", new ContactoForm());
        }
        return "cliente/contacto";
    }

    @PostMapping
    public String enviar(@Valid @ModelAttribute("contacto") ContactoForm form,
            BindingResult br,
            RedirectAttributes ra) {
        if (br.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.contacto", br);
            ra.addFlashAttribute("contacto", form);
            return "redirect:/contacto";
        }
        contactoService.procesar(form);
        ra.addFlashAttribute("ok", "¡Gracias! Te contactaremos pronto.");
        return "redirect:/contacto";
    }
}
