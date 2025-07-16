package com.tiendatech.service;

import com.tiendatech.domain.Oferta;

import java.util.List;

public interface OfertaService {

    List<Oferta> listar();

    Oferta buscarPorId(Long id);

    void guardar(Oferta oferta);

    void eliminar(Long id);
}
