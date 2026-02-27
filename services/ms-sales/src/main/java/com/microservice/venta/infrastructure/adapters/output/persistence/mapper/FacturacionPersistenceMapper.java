package com.microservice.venta.infrastructure.adapters.output.persistence.mapper;

import com.microservice.venta.domain.model.DetalleFacturaModel;
import com.microservice.venta.domain.model.FacturacionModel;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.DetalleFacturaEntity;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.FacturacionEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class FacturacionPersistenceMapper {

    public static FacturacionModel toResponse(FacturacionEntity entity){

        List<DetalleFacturaModel> detallesResponse = entity.getDetalles() != null ?
                entity.getDetalles().stream()
                        .map(DetalleFacturaPersistenceMapper::toResponse)
                        .toList() : List.of();

        return FacturacionModel.builder()
                .id(entity.getId())
                .numeroFactura(entity.getNumeroFactura())
                .fechaFactura(entity.getFechaFactura())
                .venta(VentaPersistenceMapper.toResponse(entity.getVenta()))
                .metodoPago(entity.getMetodoPago())
                .total(entity.getTotal())
                .subtotal(entity.getSubtotal())
                .impuestos(entity.getImpuestos())
                .activo(entity.isActivo())
                .estado(entity.getEstado())
                .detalles(detallesResponse)
                .build();
    }

    public static FacturacionEntity toEntity(FacturacionModel model){

        List<DetalleFacturaEntity> detalles = model.getDetalles() != null ?
                model.getDetalles().stream()
                        .map(DetalleFacturaPersistenceMapper::toEntity)
                        .toList() : List.of();

        return FacturacionEntity.builder()
                .numeroFactura(model.getNumeroFactura())
                .fechaFactura(model.getFechaFactura())
                .venta(VentaPersistenceMapper.toEntity(model.getVenta()))
                .metodoPago(model.getMetodoPago())
                .total(model.getTotal())
                .subtotal(model.getSubtotal())
                .impuestos(model.getImpuestos())
                .activo(model.isActivo())
                .estado(model.getEstado())
                .detalles(detalles)
                .build();
    }

}
