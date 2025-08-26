package com.tiendatech.service;

import com.tiendatech.domain.FAQ;

import java.util.List;

public interface FAQService {

    List<FAQ> listar();

    List<FAQ> listarActivasOrden();

    FAQ buscarPorId(Long id);

    void guardar(FAQ faq);

    void eliminar(Long id);
}
