package com.microservice.inventario.infrastructure.adapters.output.persistence.mapper;

import com.microservice.inventario.domain.model.Inventario;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.InventarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface InventarioPersistenceMapper {

    @Mapping(source = "producto.id", target = "productoId")
    InventarioEntity toInventarioEntity(Inventario inventario);
    @Mapping(source = "productoId", target = "producto", qualifiedByName = "mapProducto")
    Inventario toInventario(InventarioEntity entity);

    List<Inventario> toInventarioList(List<InventarioEntity> entityList);

    @Named("mapProducto")
    default Producto mapProducto(UUID productoId) {
        if (productoId == null) return null;
        return Producto.builder().id(productoId).build();
    }
}

