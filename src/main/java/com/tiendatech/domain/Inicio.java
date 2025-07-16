package com.tiendatech.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Inicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String bienvenidaTexto;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String imagenBanner;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String ubicacionMapa; // URL del mapa o la dirección de la tienda
}
