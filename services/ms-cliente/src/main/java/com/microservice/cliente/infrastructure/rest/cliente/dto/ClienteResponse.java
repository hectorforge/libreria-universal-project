package com.microservice.cliente.infrastructure.rest.cliente.dto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response con información del cliente")
public record ClienteResponse(

        @Schema(description = "Identificador único del cliente", defaultValue = "123e4567-e89b-12d3-a456-426614174000")
        String id,

        @Schema(description = "Apellidos del cliente", defaultValue = "Pérez")
        String apellidos,

        @Schema(description = "Nombres del cliente", defaultValue = "Juan")
        String nombre,

        @Schema(description = "Correo electrónico del cliente", defaultValue = "juan.perez@email.com")
        String email,

        @Schema(description = "Tipo de cliente", defaultValue = "PERSONA_NATURAL")
        String tipoPersona,

        @Schema(description = "Tipo de documento del cliente", defaultValue = "DNI")
        String tipoDocumento,

        @Schema(description = "Rol del cliente", defaultValue = "USUARIO")
        String rol,

        @Schema(description = "Identificador en Keycloak", defaultValue = "keycloak-uuid-1234")
        String keycloakId,

        @Schema(description = "Fecha de creación del registro", defaultValue = "2026-02-07T12:30:00")
        String dateCreated,

        @Schema(description = "Fecha de última actualización del registro", defaultValue = "2026-02-07T12:45:00")
        String dateUpdated,

        @Schema(description = "Estado de activación", defaultValue = "true")
        String isActive,

        @Schema(description = "Estado de eliminación lógica", defaultValue = "false")
        String isDeleted

) {}