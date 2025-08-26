package com.tiendatech.controller;

import com.tiendatech.dao.CategoriaDao;
import com.tiendatech.dao.ProductoDao;
import com.tiendatech.dao.ProductoOfertaDao;
import com.tiendatech.dao.CarritoItemDao;
import com.tiendatech.domain.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/productos")
public class ProductoAdminController {

    @Autowired
    private ProductoDao productoDao;
    @Autowired
    private ProductoOfertaDao productoOfertaDao;
    @Autowired
    private CategoriaDao categoriaDao;
    @Autowired
    private CarritoItemDao carritoItemDao;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoDao.findAll());
        return "admin/productos";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaDao.findAll());
        return "admin/producto_form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoDao.save(producto);
        return "redirect:/admin/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoDao.findById(id).orElse(null));
        model.addAttribute("categorias", categoriaDao.findAll());
        return "admin/producto_form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, Model model) {

        if (productoOfertaDao.existsByProducto_Id(id)) {
            model.addAttribute("error", "El producto no puede eliminarse porque está asociado a una oferta.");
            model.addAttribute("productos", productoDao.findAll());
            return "admin/productos";
        }

        if (carritoItemDao.existsByProducto_Id(id)) {
            model.addAttribute("error", "No se puede eliminar un producto que está en carritos activos.");
            model.addAttribute("productos", productoDao.findAll());
            return "admin/productos";
        }

        try {
            productoDao.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("error", "El producto no puede eliminarse porque está referenciado (carritos/ofertas/pedidos).");
        }

        model.addAttribute("productos", productoDao.findAll());
        return "admin/productos";
    }
}
