package com.tiendatech.controller;

import com.tiendatech.dao.CarritoItemDao;
import com.tiendatech.dao.ClienteDao;
import com.tiendatech.domain.Carrito;
import com.tiendatech.domain.CarritoItem;
import com.tiendatech.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Controller
public class CarritoClienteController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private CarritoItemDao itemDao;

    @Autowired
    private ClienteDao clienteDao;

    /**
     * Obtiene el ID del cliente autenticado (username = email).
     */
    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return null; // no autenticado
        }
        String email = auth.getName(); // en tu login el username es el email
        return clienteDao.findByEmail(email)
                .map(c -> c.getId())
                .orElse(null);
    }

    @GetMapping("/carrito")
    public String verCarrito(Model model) {
        Long userId = getCurrentUserId();
        if (userId == null) {
            // si no está logueado, lo mandamos al login de cliente
            return "redirect:/cliente/login_cliente";
        }

        Carrito carrito = carritoService.obtenerCarritoAbierto(userId);

        List<CarritoItem> items = (carrito != null)
                ? itemDao.findByCarritoId(carrito.getId())
                : Collections.emptyList();

        BigDecimal total = items.stream()
                .map(i -> i.getPrecioUnitario()
                .multiply(BigDecimal.valueOf(i.getCantidad().longValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int totalItems = items.stream().mapToInt(CarritoItem::getCantidad).sum();

        model.addAttribute("carrito", carrito);
        model.addAttribute("items", items);
        model.addAttribute("total", total);
        model.addAttribute("totalItems", totalItems);

        return "cliente/carrito";
    }

    @PostMapping("/carrito/agregar")
    public String agregar(@RequestParam Long idProducto,
            @RequestParam(required = false) String color,
            @RequestParam(defaultValue = "1") int cantidad) {
        Long userId = getCurrentUserId();
        if (userId == null) {
            return "redirect:/cliente/login_cliente";
        }

        if (cantidad < 1) {
            cantidad = 1;
        }
        if (color == null) {
            color = "";
        }

        carritoService.agregarItem(userId, idProducto, color, cantidad);
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/quitar/{idItem}")
    public String quitar(@PathVariable Long idItem) {
        Long userId = getCurrentUserId();
        if (userId == null) {
            return "redirect:/cliente/login_cliente";
        }
        carritoService.quitarItem(idItem);
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/vaciar")
    public String vaciar() {
        Long userId = getCurrentUserId();
        if (userId == null) {
            return "redirect:/cliente/login_cliente";
        }
        Carrito carrito = carritoService.obtenerCarritoAbierto(userId);
        if (carrito != null) {
            carritoService.vaciar(carrito.getId());
        }
        return "redirect:/carrito";
    }
}
