package com.tiendatech.service.impl;

import com.tiendatech.dao.OfertaDao;
import com.tiendatech.domain.Oferta;
import com.tiendatech.service.OfertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    private OfertaDao ofertaDao;

    @Override
    public List<Oferta> listar() {
        return ofertaDao.findAll();
    }

    @Override
    public Oferta buscarPorId(Long id) {
        return ofertaDao.findById(id).orElse(null);
    }

    @Override
    public void guardar(Oferta oferta) {
        ofertaDao.save(oferta);
    }

    @Override
    public void eliminar(Long id) {
        ofertaDao.deleteById(id);
    }
}
