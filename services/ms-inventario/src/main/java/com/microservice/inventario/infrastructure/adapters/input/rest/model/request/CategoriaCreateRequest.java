package com.microservice.inventario.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaCreateRequest {

    @NotBlank(message = "El nombre de la categoría no puede ser vacío")
    private String nombre;

    @NotBlank(message = "La descripción de la categoría no puede ser vacía")
    private String descripcion;

    @NotBlank(message = "El estado de la categoría no puede ser vacía")
    private boolean estado;
}
