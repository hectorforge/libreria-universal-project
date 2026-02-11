package com.microservice.cliente.infrastructure.rest.cliente.dto;

import com.microservice.cliente.infrastructure.rest.cliente.validators.ActualizarClienteGrupo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

@Schema(description = "Request para crear o actualizar un cliente")
public record ClienteRequest(

        @Schema(
                description = "Identificador único del cliente",
                defaultValue = "123e4567-e89b-12d3-a456-426614174000"
        )
        @NotBlank(message = "El ID del cliente es obligatorio para actualizar", groups = ActualizarClienteGrupo.class)
        String id,

        @Schema(
                description = "Apellidos del cliente",
                defaultValue = "Pérez"
        )
        @NotBlank(message = "Los apellidos son obligatorios")
        @Size(max = 100, message = "Los apellidos no pueden exceder los 100 caracteres")
        String apellidos,

        @Schema(
                description = "Nombres del cliente",
                defaultValue = "Juan"
        )
        @NotBlank(message = "Los nombres son obligatorios")
        @Size(max = 100, message = "Los nombres no pueden exceder los 100 caracteres")
        String nombre,

        @Schema(
                description = "Correo electrónico del cliente",
                defaultValue = "juan.perez@email.com"
        )
        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "Formato de correo inválido")
        String email,

        @Schema(
                description = "Tipo de cliente",
                defaultValue = "NATURAL"
        )
        @NotBlank(message = "El tipo de cliente es obligatorio")
        String tipoPersona,

        @Schema(
                description = "Tipo de documento del cliente",
                defaultValue = "DNI"
        )
        String tipoDocumento,

        @Schema(
                description = "Rol del cliente",
                defaultValue = "USUARIO"
        )
        String rol,

        @Schema(
                description = "Identificador en Keycloak",
                defaultValue = "keycloak-uuid-1234"
        )
        String keycloakId

) {}
