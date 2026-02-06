package com.microservice.inventario.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoInventarioCreateRequest {

    @NotNull(message = "El producto es obligatorio")
    private UUID productoId;

    @NotNull(message = "El tipo de movimiento es obligatorio")
    private TipoMovimiento tipo;

    @Positive(message = "La cantidad debe ser mayor a cero")
    private int cantidad;

    public enum TipoMovimiento {
        ENTRADA,
        SALIDA
    }
}
