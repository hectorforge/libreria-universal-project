package com.microservice.cliente.domain.cliente.utils;

import com.microservice.cliente.domain.common.TipoCliente;

public record ClienteFiltro(
        String apellidos,
        String nombres,
        String email,
        TipoCliente tipoCliente
) {
}
