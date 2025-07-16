package com.tiendatech.dao;

import com.tiendatech.domain.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaDao extends JpaRepository<Oferta, Long> {
}
