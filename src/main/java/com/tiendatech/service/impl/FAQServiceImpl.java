package com.tiendatech.service.impl;

import com.tiendatech.dao.FAQDao;
import com.tiendatech.domain.FAQ;
import com.tiendatech.service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQServiceImpl implements FAQService {

    @Autowired
    private FAQDao faqDao;

    @Override
    public List<FAQ> listar() {
        return faqDao.findAll();
    }

    @Override
    public List<FAQ> listarActivasOrden() {
        return faqDao.findAllByActivaTrueOrderByOrdenAscIdAsc();
    }

    @Override
    public FAQ buscarPorId(Long id) {
        return faqDao.findById(id).orElse(null);
    }

    @Override
    public void guardar(FAQ faq) {
        faqDao.save(faq);
    }

    @Override
    public void eliminar(Long id) {
        faqDao.deleteById(id);
    }
}
