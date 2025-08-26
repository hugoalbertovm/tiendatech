package com.tiendatech.service;

import com.tiendatech.dao.ContactoForm;
import com.tiendatech.domain.Contacto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactoService {

    void procesar(ContactoForm form);

    Page<Contacto> listar(Pageable pageable);

    Contacto buscarPorId(Long id);

    void cambiarEstado(Long id, Contacto.Estado estado);

    void eliminar(Long id);
}
