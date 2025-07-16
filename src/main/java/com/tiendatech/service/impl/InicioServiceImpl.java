package com.tiendatech.service.impl;

import com.tiendatech.dao.InicioDao;
import com.tiendatech.domain.Inicio;
import com.tiendatech.service.InicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InicioServiceImpl implements InicioService {

    @Autowired
    private InicioDao inicioDao;

    @Override
    public Iterable<Inicio> listar() {
        return inicioDao.findAll();
    }

    @Override
    public Optional<Inicio> obtenerPorId(Long id) {
        return inicioDao.findById(id);
    }

    @Override
    public Inicio guardar(Inicio inicio) {
        return inicioDao.save(inicio);
    }

    @Override
    public void eliminar(Long id) {
        inicioDao.deleteById(id);
    }

    @Override
    public Optional<Inicio> obtenerPrimerRegistro() {
        return inicioDao.findTopByOrderByIdAsc();
    }
}
