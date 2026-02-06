package com.microservice.inventario.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private UUID id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private double precioActual;
    private boolean estado;
    private String urlImagen;

}
