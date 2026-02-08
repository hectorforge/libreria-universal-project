package com.microservice.venta.infrastructure.adapters.input.rest.model.response;

import com.microservice.venta.domain.enums.Estado;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VentaResponse {

    private UUID id;
    private String codigo;
    private UUID clienteId;
    private Double total;
    private LocalDate fecha;
    private boolean activo;
    private Estado estado;

    private List<DetalleVentaResponse> detalles;

}
