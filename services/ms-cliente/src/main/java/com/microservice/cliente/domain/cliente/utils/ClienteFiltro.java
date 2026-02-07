package com.microservice.cliente.domain.cliente.utils;

public record ClienteFiltro(
        String apellidos,
        String nombres,
        String email,
        String tipoCliente
) {
}
