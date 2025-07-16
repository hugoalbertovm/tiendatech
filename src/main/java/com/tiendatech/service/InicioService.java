package com.tiendatech.service;

import com.tiendatech.domain.Inicio;
import java.util.Optional;

public interface InicioService {

    Iterable<Inicio> listar();

    Optional<Inicio> obtenerPorId(Long id);

    Inicio guardar(Inicio inicio);

    void eliminar(Long id);

    Optional<Inicio> obtenerPrimerRegistro();
}
