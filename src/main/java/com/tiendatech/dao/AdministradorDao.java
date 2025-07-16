package com.tiendatech.dao;

import com.tiendatech.domain.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorDao extends JpaRepository<Administrador, Long> {

    Administrador findByEmail(String email);
}
