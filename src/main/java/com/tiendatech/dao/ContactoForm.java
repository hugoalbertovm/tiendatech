package com.tiendatech.dao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactoForm {

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @Email(message = "Ingresa un email válido.")
    @NotBlank(message = "El email es obligatorio.")
    private String email;

    @NotBlank(message = "El mensaje es obligatorio.")
    @Size(max = 1000, message = "El mensaje no debe superar 1000 caracteres.")
    private String mensaje;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
