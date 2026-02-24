package com.microservice.venta.infrastructure.adapters.input.rest.model.response.feign;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleVentaCompletaFeign {

    private Integer id;
    private UUID productoId;
    private String nombreProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

}
