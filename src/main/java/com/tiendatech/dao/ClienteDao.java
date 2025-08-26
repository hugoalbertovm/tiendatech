package com.tiendatech.dao;

import com.tiendatech.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteDao extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);
}
