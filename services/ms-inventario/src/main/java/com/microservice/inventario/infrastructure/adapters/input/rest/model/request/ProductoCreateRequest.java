package com.microservice.inventario.infrastructure.adapters.input.rest.model.request;

import com.microservice.inventario.domain.model.Categoria;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.CategoriaEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoCreateRequest {

    @NotBlank(message = "El código del producto no puede ser vacío")
    private String codigo;

    @NotBlank(message = "El nombre del producto no puede ser vacío")
    private String nombre;

    @NotBlank(message = "La descripción no puede ser vacía")
    private String descripcion;

    @Positive(message = "El precio debe ser mayor a cero")
    private double precioActual;

    @NotNull(message = "La categoría es obligatoria")
    private Categoria categoria;// solo el ID de la categoría

    private String urlImagen;
}
