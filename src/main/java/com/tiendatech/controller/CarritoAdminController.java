package com.tiendatech.controller;

import com.tiendatech.dao.CarritoDao;
import com.tiendatech.dao.CarritoItemDao;
import com.tiendatech.dao.ClienteDao;
import com.tiendatech.domain.Carrito;
import com.tiendatech.domain.EstadoCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/admin/carrito")
public class CarritoAdminController {

    @Autowired
    private CarritoDao carritoDao;
    @Autowired
    private CarritoItemDao itemDao;
    @Autowired
    private ClienteDao clienteDao;

    @GetMapping
    public String listar(Model model, @RequestParam(required = false) String error) {
        var carritos = carritoDao.findAll();

        var itemsCount = carritos.stream()
                .collect(java.util.stream.Collectors.toMap(
                        Carrito::getId,
                        c -> itemDao.countByCarritoId(c.getId())
                ));

        model.addAttribute("carrito", carritos);
        model.addAttribute("itemsCount", itemsCount);
        model.addAttribute("error", error);
        return "admin/carrito";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("carrito", new Carrito());
        model.addAttribute("usuarios", clienteDao.findAll());
        model.addAttribute("estados", EstadoCarrito.values());
        return "admin/carrito_form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Carrito carrito) {
        if (carrito.getEstado() == null) {
            carrito.setEstado(EstadoCarrito.ABIERTO);
        }
        carritoDao.save(carrito);
        return "redirect:/admin/carrito";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("carrito", carritoDao.findById(id).orElse(null));
        model.addAttribute("usuarios", clienteDao.findAll());
        model.addAttribute("estados", EstadoCarrito.values());
        return "admin/carrito_form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        if (itemDao.existsByCarritoId(id)) {
            String msg = UriUtils.encode("No se puede eliminar un carrito con ítems asociados.", StandardCharsets.UTF_8);
            return "redirect:/admin/carrito?error=" + msg;
        }
        carritoDao.deleteById(id);
        return "redirect:/admin/carrito";
    }
}
