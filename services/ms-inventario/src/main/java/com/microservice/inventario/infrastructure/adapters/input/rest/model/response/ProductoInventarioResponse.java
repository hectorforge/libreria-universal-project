package com.microservice.inventario.infrastructure.adapters.input.rest.model.response;

import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoInventarioResponse{
    private UUID productoId;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precioActual;
    private boolean estado;
    private String urlImagen;
    private UUID categoriaId;
    private String categoriaNombre;
    private int stockActual;
    private int stockMinimo;

}
