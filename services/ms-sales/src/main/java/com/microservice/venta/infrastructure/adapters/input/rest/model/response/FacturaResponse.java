package com.microservice.venta.infrastructure.adapters.input.rest.model.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacturaResponse {

    private UUID id;
    private String numeroFactura;
    private LocalDate fechaFactura;
    private UUID idVenta;
    private String metodoPago;
    private Double total;
    private Double subtotal;
    private Double impuestos;
    private boolean activo;
    private String estado;

    List<DetalleFacturaResponse> detalles;

}
