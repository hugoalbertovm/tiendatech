package com.tiendatech.service.impl;

import com.tiendatech.dao.ContactoDao;
import com.tiendatech.dao.ContactoForm;
import com.tiendatech.domain.Contacto;
import com.tiendatech.service.ContactoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactoServiceImpl implements ContactoService {

    private static final Logger log = LoggerFactory.getLogger(ContactoServiceImpl.class);
    private final ContactoDao dao;

    public ContactoServiceImpl(ContactoDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void procesar(ContactoForm form) {
        Contacto c = new Contacto();
        c.setNombre(form.getNombre());
        c.setCorreo(form.getEmail());
        c.setMensaje(form.getMensaje());
        dao.guardar(c);
        log.info("[Contacto] Guardado: {} <{}>", c.getNombre(), c.getCorreo());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Contacto> listar(Pageable pageable) {
        return dao.listar(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Contacto buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    @Override
    @Transactional
    public void cambiarEstado(Long id, Contacto.Estado estado) {
        dao.actualizarEstado(id, estado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        dao.eliminar(id);
    }
}
