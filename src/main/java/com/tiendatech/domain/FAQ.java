package com.tiendatech.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "faq")
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La pregunta es obligatoria")
    private String pregunta;

    @NotBlank(message = "La respuesta es obligatoria")
    @Column(columnDefinition = "TEXT")
    private String respuesta;

    private Boolean activa = true;

    private Integer orden = 0;
}
