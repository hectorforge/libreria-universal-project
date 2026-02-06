package com.microservice.inventario.infrastructure.adapters.input.rest.model.response;

import java.util.UUID;

public record ProductoInventarioResponse(
        UUID productoId,
        String codigo,
        String nombre,
        String descripcion,
        double precioActual,
        boolean estado,
        String urlImagen,
        UUID categoriaId,
        String categoriaNombre,
        int stockActual,
        int stockMinimo
) {}
