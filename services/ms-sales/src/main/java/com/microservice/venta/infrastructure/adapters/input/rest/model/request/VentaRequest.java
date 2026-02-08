package com.microservice.venta.infrastructure.adapters.input.rest.model.request;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VentaRequest {

    private UUID clienteId;

    private List<DetalleVentaRequest> detalleVenta;

}
