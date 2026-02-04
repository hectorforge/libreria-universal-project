package com.microservice.venta.infrastructure.adapters.output.persistence.mapper;

import com.microservice.venta.domain.model.DetalleVentaModel;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.DetalleVentaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DetalleVentaPersistenceMapper {

    public static DetalleVentaModel toResponse (DetalleVentaEntity entity) {
        return DetalleVentaModel.builder()
                .id(entity.getId())
                .productoId(entity.getProductoId())
                .cantidad(entity.getCantidad())
                .precioUnitario(entity.getPrecioUnitario())
                .subtotal(entity.getSubtotal())
                .build();
    }

    public static DetalleVentaEntity toEntity(DetalleVentaModel model) {
        return DetalleVentaEntity.builder()
                .productoId(model.getProductoId())
                .cantidad(model.getCantidad())
                .precioUnitario(model.getPrecioUnitario())
                .subtotal(model.getSubtotal())
                .build();
    }
}
