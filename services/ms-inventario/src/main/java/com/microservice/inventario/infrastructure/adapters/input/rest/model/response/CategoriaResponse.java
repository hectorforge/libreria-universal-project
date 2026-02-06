package com.microservice.inventario.infrastructure.adapters.input.rest.model.response;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponse {

    private UUID id;
    private String nombre;
    private String descripcion;
    private boolean estado;

}
