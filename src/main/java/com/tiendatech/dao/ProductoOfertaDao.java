package com.tiendatech.dao;

import com.tiendatech.domain.ProductoOferta;
import com.tiendatech.domain.ProductoOfertaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoOfertaDao extends JpaRepository<ProductoOferta, ProductoOfertaId> {

    boolean existsByProducto_Id(Long productoId);
}
