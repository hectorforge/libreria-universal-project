package com.microservice.venta.infrastructure.adapters.input.rest.mapper;

import com.microservice.venta.domain.model.DetalleFacturaModel;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.DetalleFacturaResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DetalleFacturaRestMapper {

    public static DetalleFacturaResponse toResponse(DetalleFacturaModel model){
        return DetalleFacturaResponse.builder()
                .id(model.getId())
                .cantidad(model.getCantidad())
                .descripcion(model.getDescripcion())
                .valorUnitario(model.getValorUnitario())
                .subtotal(model.getSubtotal())
                .build();
    }

}
