package com.tiendatech.dao;

import com.tiendatech.domain.Contacto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ContactoDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void guardar(Contacto c) {
        em.persist(c);
    }

    @Transactional(readOnly = true)
    public Page<Contacto> listar(Pageable pageable) {
        String jpql = "SELECT c FROM Contacto c ORDER BY c.creadoEn DESC";
        TypedQuery<Contacto> q = em.createQuery(jpql, Contacto.class);
        q.setFirstResult((int) pageable.getOffset());
        q.setMaxResults(pageable.getPageSize());
        List<Contacto> data = q.getResultList();

        Long total = em.createQuery("SELECT COUNT(c) FROM Contacto c", Long.class).getSingleResult();
        return new PageImpl<>(data, pageable, total);
    }

    @Transactional(readOnly = true)
    public Contacto buscarPorId(Long id) {
        return em.find(Contacto.class, id);
    }

    @Transactional
    public void actualizarEstado(Long id, Contacto.Estado estado) {
        Contacto c = em.find(Contacto.class, id);
        if (c != null) {
            c.setEstado(estado);
            em.merge(c);
        }
    }

    @Transactional
    public void eliminar(Long id) {
        Contacto c = em.find(Contacto.class, id);
        if (c != null) {
            em.remove(c);
        }
    }
}
