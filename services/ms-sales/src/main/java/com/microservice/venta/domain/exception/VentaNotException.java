package com.microservice.venta.domain.exception;

public class VentaNotException extends RuntimeException {
    public VentaNotException(String message) {
        super(message);
    }
}
