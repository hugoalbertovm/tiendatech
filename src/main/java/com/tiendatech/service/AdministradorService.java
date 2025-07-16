package com.tiendatech.service;

import com.tiendatech.domain.Administrador;

import java.util.List;

public interface AdministradorService {

    List<Administrador> listar();

    Administrador buscarPorId(Long id);

    Administrador buscarPorEmail(String email);

    void guardar(Administrador administrador);

    void eliminar(Long id);
}
