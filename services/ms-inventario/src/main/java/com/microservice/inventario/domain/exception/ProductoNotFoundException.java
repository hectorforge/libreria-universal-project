package com.microservice.inventario.domain.exception;

public class ProductoNotFoundException extends RuntimeException{
    public ProductoNotFoundException() {
        super("Producto no encontrado");
    }
}
