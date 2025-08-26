package com.tiendatech.dao;

import com.tiendatech.domain.CarritoItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoItemDao extends JpaRepository<CarritoItem, Long> {

    List<CarritoItem> findByCarritoId(Long idCarrito);

    boolean existsByCarritoId(Long idCarrito);

    Optional<CarritoItem> findByCarritoIdAndProductoIdAndColor(Long idCarrito, Long idProducto, String color);

    long countByCarritoId(Long idCarrito);
}
