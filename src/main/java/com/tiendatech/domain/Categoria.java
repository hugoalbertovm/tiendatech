package com.tiendatech.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String imagen;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;

    @Override
    public String toString() {
        return "Categoria{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", productos=" + (productos != null ? "size: " + productos.size() : "null")
                + '}';
    }

}
