package com.tiendatech.dao;

import com.tiendatech.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDao extends JpaRepository<Cliente, Long> {

    Cliente findByEmail(String email);
}
