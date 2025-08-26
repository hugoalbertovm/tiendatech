package com.tiendatech.service.impl;

import com.tiendatech.dao.ClienteDao;
import com.tiendatech.domain.Cliente;
import com.tiendatech.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarPorEmail(String email) {
        return clienteDao.findByEmail(email).orElse(null);
    }

    @Override
    @Transactional
    public void guardar(Cliente cliente) {
        clienteDao.findByEmail(cliente.getEmail()).ifPresent(c -> {
            throw new IllegalStateException("El correo ya está registrado");
        });

        cliente.setClave(passwordEncoder.encode(cliente.getClave()));
        clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        clienteDao.deleteById(id);
    }
}
