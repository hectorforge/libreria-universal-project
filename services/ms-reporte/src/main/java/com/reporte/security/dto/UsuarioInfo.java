package com.reporte.security.dto;

import java.util.List;

public record UsuarioInfo(
        String id,
        String email,
        String nombre,
        String apellido,
        String nombreCompleto,
        String tipoPersona,
        String tipoDocumento,
        String numeroDocumento,
        List<String> roles
) {}