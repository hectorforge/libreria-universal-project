package com.microservice.venta.domain.exception;

public class PagoNotException extends RuntimeException {
    public PagoNotException(String message) {
        super(message);
    }
}
