package com.microservice.venta.infrastructure.adapters.output.persistence.mapper;

import com.microservice.venta.domain.model.DetalleVentaModel;
import com.microservice.venta.domain.model.VentaModel;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.DetalleVentaEntity;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.VentaEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class VentaPersistenceMapper {

    public static VentaModel toResponse (VentaEntity ventaEntity) {

        List<DetalleVentaModel> detallesResponse = ventaEntity.getDetalles() != null ?
                ventaEntity.getDetalles().stream()
                        .map(DetalleVentaPersistenceMapper::toResponse)
                        .toList() : List.of();

        return VentaModel.builder()
                .id(ventaEntity.getId())
                .codigo(ventaEntity.getCodigo())
                .clienteId(ventaEntity.getClienteId())
                .total(ventaEntity.getTotal())
                .fecha(ventaEntity.getFecha())
                .activo(ventaEntity.isActivo())
                .estado(ventaEntity.getEstado())
                .detalles(detallesResponse)
                .build();
    }

    public static VentaEntity toEntity(VentaModel ventaModel) {

        List<DetalleVentaEntity> detalles = ventaModel.getDetalles() != null ?
                ventaModel.getDetalles().stream()
                        .map(DetalleVentaPersistenceMapper::toEntity)
                        .toList() : List.of();

        return VentaEntity.builder()
                .codigo(ventaModel.getCodigo())
                .clienteId(ventaModel.getClienteId())
                .total(ventaModel.getTotal())
                .fecha(ventaModel.getFecha())
                .activo(ventaModel.isActivo())
                .estado(ventaModel.getEstado())
                .detalles(detalles)
                .build();
    }

}
