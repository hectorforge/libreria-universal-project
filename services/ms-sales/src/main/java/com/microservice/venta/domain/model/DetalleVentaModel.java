package com.microservice.venta.domain.model;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaModel {

    private Integer id;
    private UUID productoId;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal; //NO DTO

    private VentaModel venta;
}
