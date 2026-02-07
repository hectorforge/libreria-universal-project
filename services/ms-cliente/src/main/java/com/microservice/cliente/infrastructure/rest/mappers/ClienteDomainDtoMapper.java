package com.microservice.cliente.infrastructure.rest.mappers;

import com.microservice.cliente.domain.cliente.Cliente;
import com.microservice.cliente.infrastructure.rest.cliente.dto.ClienteRequest;
import com.microservice.cliente.infrastructure.rest.cliente.dto.ClienteResponse;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ClienteDomainDtoMapper {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Mapear de {@link ClienteRequest} a {@link Cliente}
     * @param request
     * @return
     */
    public Cliente toDomain(ClienteRequest request) {
        if (request == null) return null;

        Cliente cliente = new Cliente();
        cliente.setId(request.id() != null ? UUID.fromString(request.id()) : null);
        cliente.setApellidos(request.apellidos());
        cliente.setNombre(request.nombre());
        cliente.setEmail(request.email());
        cliente.setRol(request.rol());
        cliente.setKeycloakId(request.keycloakId());

        if (request.tipoPersona() != null)
            cliente.setTipoPersona(Enum.valueOf(com.microservice.cliente.domain.common.TipoCliente.class, request.tipoPersona()));

        if (request.tipoDocumento() != null)
            cliente.setTipoDocumento(Enum.valueOf(com.microservice.cliente.domain.common.TipoDocumento.class, request.tipoDocumento()));

        return cliente;
    }

    /**
     * Mapear de {@link Cliente} a {@link ClienteResponse}
     * @param cliente
     * @return
     */
    public ClienteResponse toResponse(Cliente cliente) {
        if (cliente == null) return null;

        return new ClienteResponse(
                cliente.getId() != null ? cliente.getId().toString() : null,
                cliente.getApellidos(),
                cliente.getNombre(),
                cliente.getEmail(),
                cliente.getTipoPersona() != null ? cliente.getTipoPersona().name() : null,
                cliente.getTipoDocumento() != null ? cliente.getTipoDocumento().name() : null,
                cliente.getRol(),
                cliente.getKeycloakId(),
                cliente.getDateCreated() != null ? cliente.getDateCreated().format(DATE_TIME_FORMATTER) : null,
                cliente.getDateUpdated() != null ? cliente.getDateUpdated().format(DATE_TIME_FORMATTER) : null,
                Boolean.toString(cliente.isActive()),
                Boolean.toString(cliente.isDeleted())
        );
    }
}
