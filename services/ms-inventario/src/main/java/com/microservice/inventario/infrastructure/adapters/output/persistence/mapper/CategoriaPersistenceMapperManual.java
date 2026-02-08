package com.microservice.inventario.infrastructure.adapters.output.persistence.mapper;

import com.microservice.inventario.domain.model.Categoria;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaPersistenceMapperManual {

    public static Categoria ToModel(CategoriaEntity categoriaEntity) {
        return Categoria.builder()
                .id(categoriaEntity.getId())
                .nombre(categoriaEntity.getNombre())
                .descripcion(categoriaEntity.getDescripcion())
                .estado(categoriaEntity.isEstado())
                .build();
    }

    public static CategoriaEntity ToEntity(Categoria categoria) {
        return CategoriaEntity.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .estado(categoria.isEstado())
                .build();
    }

}
