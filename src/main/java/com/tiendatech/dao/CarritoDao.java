package com.tiendatech.dao;

import com.tiendatech.domain.Carrito;
import com.tiendatech.domain.EstadoCarrito;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoDao extends JpaRepository<Carrito, Long> {

    Optional<Carrito> findFirstByUsuarioIdAndEstadoOrderByCreadoEnDesc(Long idUsuario, EstadoCarrito estado);
}
