package com.microservice.inventario.infrastructure.adapters.input.rest.mapper;

import com.microservice.inventario.domain.model.Categoria;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.request.CategoriaCreateRequest;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.CategoriaResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoriaResponseMapperManual {

    public static CategoriaResponse toCategoriaResponse(Categoria categoria) {

        if (categoria == null) {
            return null;
        }

        return CategoriaResponse.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .estado(categoria.isEstado())
                .build();
    }

    public static Categoria toModel(CategoriaCreateRequest request) {

        if (request == null) {
            return null;
        }

        return Categoria.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .estado(true) // Por defecto, la categoría se crea como activa
                .build();
    }




}
