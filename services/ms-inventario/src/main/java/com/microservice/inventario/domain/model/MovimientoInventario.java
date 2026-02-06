package com.microservice.inventario.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventario {

    private UUID id;
    private Producto producto;
    private TipoMovimiento tipo;
    private int cantidad;
    private LocalDateTime fecha;

    public enum TipoMovimiento {
        ENTRADA,
        SALIDA
    }

}