package com.microservice.inventario.infrastructure.adapters.input.rest.mapper;

import com.microservice.inventario.domain.model.Inventario;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.request.InventarioCreateRequest;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.InventarioProductoResponse;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.InventarioResponse;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.ProductoResponse;
import org.springframework.stereotype.Component;

@Component
public class InventarioRestMapperManual {

    public static InventarioResponse toInventarioResponse(Inventario inventario) {

        ProductoResponse productoResponse = ProductoRestMapperManual.toResponse(
                inventario.getProductoId());

        return InventarioResponse.builder()
                .idInventario(inventario.getId())
                .producto(productoResponse)
                .stockActual(inventario.getStockActual())
                .stockMinimo(inventario.getStockMinimo())
                .build();
    }

    public static Inventario toModel(InventarioCreateRequest request) {

        Producto producto = new Producto();
        producto.setId(request.getProductoId());

        return Inventario.builder()
                .productoId(producto)
                .stockActual(request.getStockActual())
                .stockMinimo(request.getStockMinimo())
                .build();
    }

    public static InventarioProductoResponse toInventarioProductoResponse(Inventario inventario) {

        Producto producto = inventario.getProductoId();

        return InventarioProductoResponse.builder()
                .idProducto(producto.getId())
                .codigoProducto(producto.getCodigo())
                .nombreProducto(producto.getNombre())
                .descripcionProducto(producto.getDescripcion())
                .precioActualProducto(producto.getPrecioActual())
                .stockActual(inventario.getStockActual())
                .stockMinimo(inventario.getStockMinimo())
                .idCategoria(producto.getCategoria().getId())
                .nombreCategoria(producto.getCategoria().getNombre())
                .descripcionCategoria(producto.getCategoria().getDescripcion())
                .build();
    }

}
