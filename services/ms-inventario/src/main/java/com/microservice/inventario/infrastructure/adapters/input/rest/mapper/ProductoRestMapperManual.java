package com.microservice.inventario.infrastructure.adapters.input.rest.mapper;

import com.microservice.inventario.domain.model.Categoria;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.request.ProductoCreateRequest;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.CategoriaResponse;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.ProductoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductoRestMapperManual {

    public static ProductoResponse toResponse(Producto producto) {

        CategoriaResponse categoriaResponse = (CategoriaResponseMapperManual.toCategoriaResponse(
                producto.getCategoria()));

        return ProductoResponse.builder()
                .id(producto.getId())
                .codigo(producto.getCodigo())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precioActual(producto.getPrecioActual())
                .categoria(categoriaResponse)
                .estado(producto.isEstado())
                .urlImagen(producto.getUrlImagen())
                .build();

    }

        public static Producto toModel(ProductoCreateRequest request) {

            Categoria categoria = new Categoria();
            categoria.setId(request.getCategoria());

            return Producto.builder()
                    .codigo(request.getCodigo())
                    .nombre(request.getNombre())
                    .descripcion(request.getDescripcion())
                    .precioActual(request.getPrecioActual())
                    .categoria(categoria)
                    .urlImagen(request.getUrlImagen())
                    .build();

        }

}
