package com.tiendatech.domain;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductoOfertaId implements Serializable {

    private Long idProducto;
    private Long idOferta;

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Long idOferta) {
        this.idOferta = idOferta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductoOfertaId that = (ProductoOfertaId) o;
        return idProducto.equals(that.idProducto) && idOferta.equals(that.idOferta);
    }

    @Override
    public int hashCode() {
        return 31 * idProducto.hashCode() + idOferta.hashCode();
    }
}
