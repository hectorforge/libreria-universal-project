package com.microservice.venta.infrastructure.adapters.input.rest.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagoResponse {

    private Integer id;
    private VentaResponse venta;
    private Double monto;
    private String metodoPago;
    private String fechaPago;
    private boolean activo;
    private String estado;

}
