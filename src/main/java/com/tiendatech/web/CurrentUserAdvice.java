package com.tiendatech.web;

import com.tiendatech.dao.ClienteDao;
import com.tiendatech.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CurrentUserAdvice {

    @Autowired
    private ClienteDao clienteDao;

    @ModelAttribute("usuarioLogueadoNombre")
    public String usuarioLogueadoNombre(Authentication auth) {
        if (auth == null) {
            return null;
        }
        String email = auth.getName();
        Cliente c = clienteDao.findByEmail(email).orElse(null);
        if (c == null) {
            return email;
        }
        String nombre = c.getNombreCompleto();
        return (nombre != null && !nombre.isBlank()) ? nombre : c.getEmail();
    }
}
