package com.microservice.inventario.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
    PRODUCTO_NOT_FOUND("INV-001", "Producto no encontrado"),
    CATEGORIA_NOT_FOUND("INV-002", "Categoría no encontrada"),
    INVENTARIO_NOT_FOUND("INV-003", "Inventario no encontrado"),
    MOVIMIENTO_NOT_FOUND("INV-004", "Movimiento de inventario no encontrado"),

    VALIDATION_ERROR("INV-400", "Errores de validación"),
    GENERIC_ERROR("INV-500", "Error interno del servidor");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
