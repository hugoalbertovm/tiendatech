package com.tiendatech.service;

import com.tiendatech.domain.Carrito;

public interface CarritoService {

    Carrito obtenerCarritoAbierto(Long idUsuario);

    Carrito agregarItem(Long idUsuario, Long idProducto, String color, int cantidad);

    void quitarItem(Long idCarritoItem);

    void vaciar(Long idCarrito);
}
