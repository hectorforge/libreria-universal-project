package com.microservice.venta.infrastructure.adapters.input.rest.mapper;

import com.microservice.venta.domain.model.FacturacionModel;
import com.microservice.venta.domain.model.VentaModel;
import com.microservice.venta.infrastructure.adapters.input.rest.model.request.FacturaRequest;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.DetalleFacturaResponse;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.DetalleVentaResponse;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.FacturaResponse;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class FacturaRestMapper {

    public static FacturaResponse toResponse(FacturacionModel model){

        List<DetalleFacturaResponse> detalles = model.getDetalles() != null ?
                model.getDetalles().stream()
                        .map(DetalleFacturaRestMapper::toResponse)
                        .toList() : List.of();

        return FacturaResponse.builder()
                .id(model.getId())
                .numeroFactura(model.getNumeroFactura())
                .fechaFactura(model.getFechaFactura())
                .idVenta(model.getVenta().getId())
                .metodoPago(model.getMetodoPago())
                .total(model.getTotal())
                .subtotal(model.getSubtotal())
                .impuestos(model.getImpuestos())
                .activo(model.isActivo())
                .estado(model.getEstado())
                .detalles(detalles)
                .build();
    }

    public static FacturacionModel toModel(FacturaRequest request){

        VentaModel venta = new VentaModel();
        venta.setId(request.getIdVenta());

        return FacturacionModel.builder()
                .venta(venta)
                .build();
    }

}
