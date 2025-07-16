package com.tiendatech.dao;

import com.tiendatech.domain.Inicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InicioDao extends JpaRepository<Inicio, Long> {

    Optional<Inicio> findTopByOrderByIdAsc();

}
