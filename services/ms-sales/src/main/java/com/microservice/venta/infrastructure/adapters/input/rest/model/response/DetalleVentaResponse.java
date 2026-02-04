package com.microservice.venta.infrastructure.adapters.input.rest.model.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaResponse {

    private Integer id;
    private UUID productoId;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

}
