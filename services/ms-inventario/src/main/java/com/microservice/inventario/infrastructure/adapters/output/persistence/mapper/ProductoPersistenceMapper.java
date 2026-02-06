package com.microservice.inventario.infrastructure.adapters.output.persistence.mapper;

import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.ProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoPersistenceMapper {


    ProductoEntity toProductoEntity(Producto producto);// aca convertiremos de un modelo producto a un productoEntity


    Producto toProducto(ProductoEntity entity);

    List<Producto> toProductoList(List<ProductoEntity> entityList); // este metodo nos servira para que nos devuelva
    // todas las entidades de la base de datos lo podemos convertir a una lista de producto de modelos
}
