package com.tiendatech.service.impl;

import com.tiendatech.dao.ProductoDao;
import com.tiendatech.domain.Producto;
import com.tiendatech.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    public List<Producto> listar() {
        return productoDao.findAll();
    }

    @Override
    public Producto buscarPorId(Long id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    public void guardar(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    public void eliminar(Long id) {
        productoDao.deleteById(id);
    }
}
