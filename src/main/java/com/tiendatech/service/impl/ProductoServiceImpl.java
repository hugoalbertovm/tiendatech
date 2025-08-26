package com.tiendatech.service.impl;

import com.tiendatech.dao.CarritoItemDao;
import com.tiendatech.dao.ProductoDao;
import com.tiendatech.dao.ProductoOfertaDao;
import com.tiendatech.domain.Producto;
import com.tiendatech.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;
    @Autowired
    private ProductoOfertaDao productoOfertaDao;
    @Autowired
    private CarritoItemDao carritoItemDao;

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
    @Transactional
    public void eliminar(Long id) {
        if (carritoItemDao.existsByProducto_Id(id)) {
            throw new IllegalStateException("No se puede eliminar un producto que está en carritos activos.");
        }
        if (productoOfertaDao.existsByProducto_Id(id)) {
            throw new IllegalStateException("El producto no puede eliminarse porque está asociado a una oferta.");
        }

        try {
            productoDao.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("El producto no puede eliminarse porque está referenciado (carritos/ofertas/pedidos).");
        }
    }
}
