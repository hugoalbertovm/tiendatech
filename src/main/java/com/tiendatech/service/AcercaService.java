package com.tiendatech.service;

import com.tiendatech.domain.Acerca;

import java.util.List;

public interface AcercaService {

    List<Acerca> listar();

    Acerca buscarPorId(Long id);

    void guardar(Acerca acerca);

    void eliminar(Long id);
}
