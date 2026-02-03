package com.microservice.venta.shared;

import lombok.Getter;

@Getter
public enum ResultCode {
    VENTA_NOT_FOUND("ERR_VENTA_001", "Venta no encontrado"),
    VENTA_INVALID("ERR_VENTA_002", "Parámetros de venta inválidos"),

    PAGO_NOT_FOUND("ERR_PAGO_001", "Pago no encontrado"),
    PAGO_INVALID("ERR_PAGO_002", "Parámetros de pago inválidos"),

    FACTURACION_NOT_FOUND("ERR_FACTURACIÓN_001", "Pago no encontrado"),
    FACTURACION_INVALID("ERR_FACTURACIÓN_002", "Parámetros de pago inválidos"),

    GENERIC_ERROR("ERR_GENERIC_001", "Ocurrió un error inesperado.");

    private final String code;
    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
