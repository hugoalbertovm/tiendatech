package com.tiendatech.service.impl;

import com.tiendatech.dao.AcercaDao;
import com.tiendatech.domain.Acerca;
import com.tiendatech.service.AcercaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcercaServiceImpl implements AcercaService {

    @Autowired
    private AcercaDao acercaDao;

    @Override
    public List<Acerca> listar() {
        return acercaDao.findAll();
    }

    @Override
    public Acerca buscarPorId(Long id) {
        return acercaDao.findById(id).orElse(null);
    }

    @Override
    public void guardar(Acerca acerca) {
        acercaDao.save(acerca);
    }

    @Override
    public void eliminar(Long id) {
        acercaDao.deleteById(id);
    }
}
