package com.microservice.venta.domain.model;

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
public class VentaModel {

    private UUID id;
    private String codigo; //NO DTO
    private UUID clienteId;
    private Double total; //NO DTO
    private LocalDate fecha; //NO DTO
    private boolean activo; //NO DTO
    private Estado estado; //NO DTO

    private List<DetalleVentaModel> detalles;

}
