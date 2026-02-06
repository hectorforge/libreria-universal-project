package com.microservice.inventario.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    private UUID id;
    private Producto producto;
    private int stockActual;
    private int stockMinimo;
}

