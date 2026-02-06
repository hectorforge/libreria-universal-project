package com.microservice.inventario.infrastructure.adapters.input.rest.model.response;

import com.microservice.inventario.domain.model.Producto;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventarioResponse {

    private UUID productoId;
    private int stockActual;
    private int stockMinimo;
}
