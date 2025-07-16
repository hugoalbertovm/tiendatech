package com.tiendatech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminDebugController {

    @GetMapping("/admin/test")
    public ResponseEntity<String> testRol() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("USUARIO AUTENTICADO: " + auth.getName());
        auth.getAuthorities().forEach(a -> System.out.println("ROL DETECTADO: " + a.getAuthority()));

        return ResponseEntity.ok("Test exitoso del login. Rol cargado correctamente.");
    }
}
