package com.tiendatech.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    private String email;

    private String clave;

    private String foto;
}
