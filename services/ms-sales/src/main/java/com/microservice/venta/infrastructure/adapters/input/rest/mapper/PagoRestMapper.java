package com.microservice.venta.infrastructure.adapters.input.rest.mapper;

import com.microservice.venta.domain.model.PagoModel;
import com.microservice.venta.domain.model.VentaModel;
import com.microservice.venta.infrastructure.adapters.input.rest.model.request.PagoRequest;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.PagoResponse;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.VentaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PagoRestMapper {

    private final VentaRestMapper ventaRestMapper;

    public PagoResponse toResponse(PagoModel pagoModel) {

        VentaResponse venta = ventaRestMapper.toResponse(pagoModel.getVenta());

        return PagoResponse.builder()
                .id(pagoModel.getId())
                .venta(venta)
                .monto(pagoModel.getMonto())
                .metodoPago(pagoModel.getMetodoPago())
                .fechaPago(pagoModel.getFechaPago().toString())
                .activo(pagoModel.isActivo())
                .estado(pagoModel.getEstado())
                .build();
    }

    public static PagoModel toRequest(PagoRequest pagoRequest) {
        return PagoModel.builder()
                .metodoPago(pagoRequest.getMetodoPago())
                .build();
    }

}
