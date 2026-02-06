package com.microservice.inventario.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    private UUID id;
    private String nombre;
    private String descripcion;
    private boolean estado;
}
