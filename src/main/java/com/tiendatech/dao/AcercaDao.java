package com.tiendatech.dao;

import com.tiendatech.domain.Acerca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcercaDao extends JpaRepository<Acerca, Long> {
}
