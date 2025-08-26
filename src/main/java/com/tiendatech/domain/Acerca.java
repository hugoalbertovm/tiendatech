package com.tiendatech.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "acerca")
public class Acerca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String intro;

    @Column(columnDefinition = "TEXT")
    private String mision;

    @Column(columnDefinition = "TEXT")
    private String vision;

    @Column(columnDefinition = "TEXT")
    private String valores;

    private String imagen;

    @Column(name = "actualizado_en", nullable = false)
    private Timestamp actualizadoEn;

    @PrePersist
    public void prePersist() {
        if (actualizadoEn == null) {
            actualizadoEn = new Timestamp(System.currentTimeMillis());
        }
    }

    @PreUpdate
    public void preUpdate() {
        actualizadoEn = new Timestamp(System.currentTimeMillis());
    }
}
