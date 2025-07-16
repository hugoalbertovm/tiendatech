package com.tiendatech.service.impl;

import com.tiendatech.dao.CategoriaDao;
import com.tiendatech.domain.Categoria;
import com.tiendatech.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    public List<Categoria> listar() {
        return categoriaDao.findAll();
    }

    @Override
    public Categoria buscarPorId(Long id) {
        return categoriaDao.findById(id).orElse(null);
    }

    @Override
    public void guardar(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    public void eliminar(Long id) {
        Categoria categoria = categoriaDao.findById(id).orElse(null);
        if (categoria != null && !categoria.getProductos().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar una categoría con productos asociados.");
        }
        categoriaDao.deleteById(id);
    }

}
