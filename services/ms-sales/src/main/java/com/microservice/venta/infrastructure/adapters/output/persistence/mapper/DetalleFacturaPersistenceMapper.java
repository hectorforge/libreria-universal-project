package com.microservice.venta.infrastructure.adapters.output.persistence.mapper;

import com.microservice.venta.domain.model.DetalleFacturaModel;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.DetalleFacturaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DetalleFacturaPersistenceMapper {

    public static DetalleFacturaModel toResponse(DetalleFacturaEntity detalleFacturaEntity){
        return DetalleFacturaModel.builder()
                .id(detalleFacturaEntity.getId())
                .cantidad(detalleFacturaEntity.getCantidad())
                .descripcion(detalleFacturaEntity.getDescripcion())
                .valorUnitario(detalleFacturaEntity.getValorUnitario())
                .subtotal(detalleFacturaEntity.getSubtotal())
                .build();
    }

    public static DetalleFacturaEntity toEntity(DetalleFacturaModel model){
        return DetalleFacturaEntity.builder()
                .cantidad(model.getCantidad())
                .descripcion(model.getDescripcion())
                .valorUnitario(model.getValorUnitario())
                .subtotal(model.getSubtotal())
                .build();
    }

}
