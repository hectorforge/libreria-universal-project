package com.microservice.inventario.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventarioCreateRequest {

    @NotNull(message = "El producto es obligatorio")
    private UUID productoId;

    @PositiveOrZero(message = "El stock actual no puede ser negativo")
    private int stockActual;

    @Positive(message = "El stock mínimo debe ser mayor a cero")
    private int stockMinimo;
}
