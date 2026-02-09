package com.microservice.venta.infrastructure.adapters.output.persistence.mapper;

import com.microservice.venta.domain.model.PagoModel;
import com.microservice.venta.domain.model.VentaModel;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.PagoEntity;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.VentaEntity;
import org.springframework.stereotype.Component;

@Component
public class PagoPersistenceMapper {

    public static PagoModel toResponse (PagoEntity pagoEntity) {

        VentaModel ventaModel = VentaPersistenceMapper.toResponse(pagoEntity.getVenta());

        return PagoModel.builder()
                .id(pagoEntity.getId())
                .venta(ventaModel)
                .monto(pagoEntity.getMonto())
                .metodoPago(pagoEntity.getMetodoPago())
                .fechaPago(pagoEntity.getFechaPago())
                .activo(pagoEntity.isActivo())
                .estado(pagoEntity.getEstado())
                .build();
    }

    public static PagoEntity toEntity (PagoModel pagoModel) {

        VentaEntity ventaEntity = VentaPersistenceMapper.toEntity(pagoModel.getVenta());

        return PagoEntity.builder()
                .id(pagoModel.getId())
                .venta(ventaEntity)
                .monto(pagoModel.getMonto())
                .metodoPago(pagoModel.getMetodoPago())
                .fechaPago(pagoModel.getFechaPago())
                .activo(pagoModel.isActivo())
                .estado(pagoModel.getEstado())
                .build();
    }

}
