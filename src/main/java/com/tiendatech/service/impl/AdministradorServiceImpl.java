package com.tiendatech.service.impl;

import com.tiendatech.dao.AdministradorDao;
import com.tiendatech.domain.Administrador;
import com.tiendatech.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private AdministradorDao administradorDao;

    @Override
    public List<Administrador> listar() {
        return administradorDao.findAll();
    }

    @Override
    public Administrador buscarPorId(Long id) {
        return administradorDao.findById(id).orElse(null);
    }

    @Override
    public Administrador buscarPorEmail(String email) {
        return administradorDao.findByEmail(email);
    }

    @Override
    public void guardar(Administrador administrador) {
        administradorDao.save(administrador);
    }

    @Override
    public void eliminar(Long id) {
        administradorDao.deleteById(id);
    }
}
