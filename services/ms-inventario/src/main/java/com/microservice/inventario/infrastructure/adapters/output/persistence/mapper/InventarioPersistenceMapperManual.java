package com.microservice.inventario.infrastructure.adapters.output.persistence.mapper;

import com.microservice.inventario.domain.model.Categoria;
import com.microservice.inventario.domain.model.Inventario;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.InventarioEntity;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.ProductoEntity;
import org.springframework.stereotype.Component;

@Component
public class InventarioPersistenceMapperManual {

    public static Inventario toModel(InventarioEntity inventario) {

        Producto producto = ProductoPersistenceMapperManual.toModel(inventario.getProductoId());

        return Inventario.builder()
                .id(inventario.getId())
                .productoId(producto)
                .stockActual(inventario.getStockActual())
                .stockMinimo(inventario.getStockMinimo())
                .build();
    }

    public static InventarioEntity toEntity(Inventario model) {

        ProductoEntity producto = ProductoPersistenceMapperManual.toEntity(model.getProductoId());

        return InventarioEntity.builder()
                .id(model.getId())
                .productoId(producto)
                .stockActual(model.getStockActual())
                .stockMinimo(model.getStockMinimo())
                .build();
    }

}
