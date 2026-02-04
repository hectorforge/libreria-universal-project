package com.microservice.venta.infrastructure.adapters.input.rest.mapper;

import com.microservice.venta.domain.model.DetalleVentaModel;
import com.microservice.venta.domain.model.VentaModel;
import com.microservice.venta.infrastructure.adapters.input.rest.model.request.VentaRequest;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.DetalleVentaResponse;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.VentaResponse;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VentaRestMapper {

    public VentaResponse toResponse(VentaModel response) {

        List<DetalleVentaResponse> detalles = response.getDetalles() != null ?
                response.getDetalles().stream()
                        .map(DetalleVentaRestMapper::toResponse)
                        .toList() : List.of();

        return VentaResponse.builder()
                .id(response.getId())
                .codigo(response.getCodigo())
                .clienteId(response.getClienteId())
                .total(response.getTotal())
                .fecha(response.getFecha())
                .activo(response.isActivo())
                .estado(response.getEstado())
                .detalles(detalles)
                .build();
    }

    public VentaModel toModel(VentaRequest request) {

        List<DetalleVentaModel> detalles = request.getDetalleVenta() != null ?
                request.getDetalleVenta().stream()
                        .map(DetalleVentaRestMapper::toModel)
                        .toList() : List.of();

        return VentaModel.builder()
                .clienteId(request.getClienteId())
                .detalles(detalles)
                .build();
    }

}
