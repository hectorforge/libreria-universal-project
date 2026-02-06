package com.microservice.inventario.infrastructure.adapters.output.persistence.mapper;

import com.microservice.inventario.domain.model.Categoria;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.CategoriaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaPersistenceMapper {

    CategoriaEntity toCategoriaEntity(Categoria categoria);

    Categoria toCategoria(CategoriaEntity entity);

    List<Categoria> toCategoriaList(List<CategoriaEntity> entityList);
}
