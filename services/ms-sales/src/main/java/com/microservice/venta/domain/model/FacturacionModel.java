package com.microservice.venta.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacturacionModel {

    private UUID id;
    private String numeroFactura;
    private LocalDate fechaFactura;
    private VentaModel venta;
    private String metodoPago;
    private Double total;
    private Double subtotal;
    private Double impuestos;
    private boolean activo;
    private String estado;

    List<DetalleFacturaModel> detalles;

}
