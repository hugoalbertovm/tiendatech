package com.tiendatech.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(unique = true)
    private String email;

    private String clave;
    private String telefono;
    private String direccion;
    private String colorPreferido;
    private String sitioWeb;
    private String salario;
    private String zona;
    private Integer satisfaccion;
    private String nivelEducativo;
    private String foto;
}
