package com.microservice.venta.domain.model;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFacturaModel {

    private Integer id;
    private Integer cantidad;
    private String descripcion;
    private Double valorUnitario;
    private Double subtotal;

    private FacturacionModel facturacion;
}
