package com.tiendatech.dao;

import com.tiendatech.domain.CarritoItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoItemDao extends JpaRepository<CarritoItem, Long> {

    List<CarritoItem> findByCarrito_Id(Long idCarrito);

    boolean existsByCarrito_Id(Long idCarrito);

    Optional<CarritoItem> findByCarrito_IdAndProducto_IdAndColor(Long idCarrito, Long idProducto, String color);

    long countByCarrito_Id(Long idCarrito);

    boolean existsByProducto_Id(Long productoId);
}
