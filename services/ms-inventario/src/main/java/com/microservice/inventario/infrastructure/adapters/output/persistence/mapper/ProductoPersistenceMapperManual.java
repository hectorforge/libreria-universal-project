package com.microservice.inventario.infrastructure.adapters.output.persistence.mapper;

import com.microservice.inventario.domain.model.Categoria;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.ProductoInventarioResponse;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.CategoriaEntity;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.ProductoEntity;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

@Component
public class ProductoPersistenceMapperManual {

    public static Producto toModel(ProductoEntity producto) {

        Categoria categoria = new Categoria();
        categoria.setId(producto.getCategoria().getId());
        categoria.setNombre(producto.getCategoria().getNombre());
        categoria.setDescripcion(producto.getCategoria().getDescripcion());
        categoria.setEstado(producto.getCategoria().isEstado());

        return Producto.builder()
                .id(producto.getId())
                .codigo(producto.getCodigo())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precioActual(producto.getPrecioActual())
                .categoria(categoria)
                .estado(producto.isEstado())
                .urlImagen(producto.getUrlImagen())
                .build();
    }

    public static ProductoEntity toEntity(Producto model) {

        CategoriaEntity categoria = CategoriaPersistenceMapperManual.ToEntity(model.getCategoria());

        return ProductoEntity.builder()
                //
                .id(model.getId())
                .codigo(model.getCodigo())
                .nombre(model.getNombre())
                .descripcion(model.getDescripcion())
                .precioActual(model.getPrecioActual())
                .categoria(categoria)
                .estado(model.isEstado())
                .urlImagen(model.getUrlImagen())
                .build();

    }

}
