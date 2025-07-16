package com.tiendatech.service.impl;

import com.tiendatech.dao.ClienteDao;
import com.tiendatech.domain.Cliente;
import com.tiendatech.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Override
    public List<Cliente> listar() {
        return clienteDao.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        return clienteDao.findByEmail(email);
    }

    @Override
    public void guardar(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    public void eliminar(Long id) {
        clienteDao.deleteById(id);
    }
}
