package com.microservice.venta.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagoModel {

    private Integer id;
    private VentaModel venta;
    private Double monto;
    private String metodoPago;
    private LocalDate fechaPago;
    private boolean activo;
    private String estado;

}
