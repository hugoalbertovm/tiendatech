package com.tiendatech.controller;

import com.tiendatech.service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FAQClienteController {

    @Autowired
    private FAQService faqService;

    @GetMapping("/faq")
    public String mostrarFaq(Model model) {
        model.addAttribute("faqs", faqService.listarActivasOrden());
        return "cliente/faq";
    }
}
