package com.microservice.venta.infrastructure.adapters.input.rest.mapper;

import com.microservice.venta.domain.model.DetalleVentaModel;
import com.microservice.venta.infrastructure.adapters.input.rest.model.request.DetalleVentaRequest;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.DetalleVentaResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DetalleVentaRestMapper {

    public static DetalleVentaResponse toResponse(DetalleVentaModel model) {
        return DetalleVentaResponse.builder()
                .id(model.getId())
                .productoId(model.getProductoId())
                .cantidad(model.getCantidad())
                .precioUnitario(model.getPrecioUnitario())
                .subtotal(model.getSubtotal())
                .build();
    }

    public static DetalleVentaModel toModel(DetalleVentaRequest request) {
        return DetalleVentaModel.builder()
                .productoId(request.getProductoId())
                .cantidad(request.getCantidad())
                .precioUnitario(request.getPrecioUnitario())
                .build();
    }

}
