package com.microservice.inventario.infrastructure.adapters.input.rest.model.response;

import com.microservice.inventario.domain.model.Categoria;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.CategoriaEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double precioActual;
    private CategoriaResponse categoria; // solo id
    private boolean estado;
    private String urlImagen;
}
