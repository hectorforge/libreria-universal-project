package com.microservice.venta.infrastructure.adapters.input.rest.model.response;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFacturaResponse {
    private Integer id;
    private Integer cantidad;
    private String descripcion;
    private Double valorUnitario;
    private Double subtotal;

}
