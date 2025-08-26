package com.tiendatech.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", length = 100)
    private String nombreCompleto;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false, length = 255)
    private String clave;

    @Lob
    private String direccion;

    @Column(length = 20)
    private String telefono;
}
