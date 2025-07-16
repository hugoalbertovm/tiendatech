package com.tiendatech.service;

import com.tiendatech.domain.Categoria;

import java.util.List;

public interface CategoriaService {

    List<Categoria> listar();

    Categoria buscarPorId(Long id);

    void guardar(Categoria categoria);

    void eliminar(Long id);
}
