package com.tiendatech.service;

import com.tiendatech.domain.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> listar();

    Cliente buscarPorId(Long id);

    Cliente buscarPorEmail(String email);

    void guardar(Cliente cliente);

    void eliminar(Long id);
}
