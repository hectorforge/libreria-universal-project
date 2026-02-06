package com.microservice.inventario.application.ports.input;

import com.microservice.inventario.domain.model.Producto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoServicePort {

    Producto registrarProducto(Producto producto);

    Optional<Producto> actualizarProducto(UUID id, Producto producto);

    void activarProducto(UUID id);//condicional aumentar

    Producto cambiarPrecio(UUID id, double nuevoPrecio);

    Producto obtenerProductoPorId(UUID id);

    List<Producto> listarProductos();

    List<Producto> listarProductosPorCategoria(UUID categoriaId);

    void eliminarProducto(UUID id);

}
