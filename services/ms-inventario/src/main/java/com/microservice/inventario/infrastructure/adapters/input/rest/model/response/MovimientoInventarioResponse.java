package com.microservice.inventario.infrastructure.adapters.input.rest.model.response;

import com.microservice.inventario.domain.model.MovimientoInventario;
import com.microservice.inventario.domain.model.Producto;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoInventarioResponse {

    private Producto producto;
    private MovimientoInventario.TipoMovimiento tipo;
    private int cantidad;
    private LocalDateTime fecha;

    public enum TipoMovimiento {
        ENTRADA,
        SALIDA
    }
}

