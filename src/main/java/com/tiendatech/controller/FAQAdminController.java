package com.tiendatech.controller;

import com.tiendatech.domain.FAQ;
import com.tiendatech.service.FAQService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/faq")
public class FAQAdminController {

    @Autowired
    private FAQService faqService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("faqs", faqService.listar());
        return "admin/faq";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("faq", new FAQ());
        return "admin/faq_form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("faq") FAQ faq, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/faq_form";
        }
        faqService.guardar(faq);
        return "redirect:/admin/faq";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("faq", faqService.buscarPorId(id));
        return "admin/faq_form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        faqService.eliminar(id);
        return "redirect:/admin/faq";
    }
}
