package com.tiendatech.service;

import com.tiendatech.domain.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> listar();

    Producto buscarPorId(Long id);

    void guardar(Producto producto);

    void eliminar(Long id);
}
