package com.tiendatech.service.impl;

import com.tiendatech.dao.CarritoDao;
import com.tiendatech.dao.CarritoItemDao;
import com.tiendatech.dao.ProductoDao;
import com.tiendatech.domain.*;
import com.tiendatech.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoDao carritoDao;
    @Autowired
    private CarritoItemDao itemDao;
    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public Carrito obtenerCarritoAbierto(Long idUsuario) {
        return carritoDao.findFirstByUsuarioIdAndEstadoOrderByCreadoEnDesc(idUsuario, EstadoCarrito.ABIERTO)
                .orElse(null);
    }

    @Override
    @Transactional
    public Carrito agregarItem(Long idUsuario, Long idProducto, String color, int cantidad) {
        color = (color == null) ? "" : color.trim();
        cantidad = Math.max(1, cantidad);

        var carrito = carritoDao.findFirstByUsuarioIdAndEstadoOrderByCreadoEnDesc(idUsuario, EstadoCarrito.ABIERTO)
                .orElseGet(() -> {
                    var c = new Carrito();
                    var u = new Cliente();
                    u.setId(idUsuario);
                    c.setUsuario(u);
                    c.setEstado(EstadoCarrito.ABIERTO);
                    return carritoDao.save(c);
                });

        var producto = productoDao.findById(idProducto).orElseThrow();

        var item = itemDao
                .findByCarrito_IdAndProducto_IdAndColor(carrito.getId(), idProducto, color)
                .orElse(null);

        if (item == null) {
            item = new CarritoItem();
            item.setCarrito(carrito);
            item.setProducto(producto);
            item.setColor(color);
            item.setCantidad(cantidad);
            item.setPrecioUnitario(producto.getPrecio());
            item.setNombreProducto(producto.getNombre());
            item.setImagen(producto.getImagen());
        } else {
            item.setCantidad(item.getCantidad() + cantidad);
        }
        itemDao.save(item);
        return carrito;
    }

    @Override
    @Transactional
    public void quitarItem(Long idCarritoItem) {
        itemDao.deleteById(idCarritoItem);
    }

    @Override
    @Transactional
    public void vaciar(Long idCarrito) {
        for (var it : itemDao.findByCarrito_Id(idCarrito)) {
            itemDao.deleteById(it.getId());
        }
    }
}
