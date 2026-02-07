package com.microservice.cliente.infrastructure.persistence.mappers;

import com.microservice.cliente.domain.cliente.Cliente;
import com.microservice.cliente.infrastructure.persistence.cliente.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteDomainEntityMapper {

    public Cliente toDomain(ClienteEntity entity) {
        if (entity == null) return null;

        Cliente cliente = new Cliente();
        cliente.setId(entity.getId());
        cliente.setDateCreated(entity.getDateCreated());
        cliente.setDateUpdated(entity.getDateUpdated());
        cliente.setCreatedBy(entity.getCreatedBy());
        cliente.setUpdatedBy(entity.getUpdatedBy());
        cliente.setActive(entity.isActive());
        cliente.setDeleted(entity.isDeleted());

        cliente.setKeycloakId(entity.getKeycloakId());
        cliente.setApellidos(entity.getApellidos());
        cliente.setNombre(entity.getNombre());
        cliente.setEmail(entity.getEmail());
        cliente.setTipoPersona(entity.getTipoPersona());
        cliente.setTipoDocumento(entity.getTipoDocumento());
        cliente.setRol(entity.getRol());

        return cliente;
    }

    public ClienteEntity toEntity(Cliente cliente) {
        if (cliente == null) return null;

        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId());
        entity.setDateCreated(cliente.getDateCreated());
        entity.setDateUpdated(cliente.getDateUpdated());
        entity.setCreatedBy(cliente.getCreatedBy());
        entity.setUpdatedBy(cliente.getUpdatedBy());
        entity.setActive(cliente.isActive());
        entity.setDeleted(cliente.isDeleted());

        entity.setKeycloakId(cliente.getKeycloakId());
        entity.setApellidos(cliente.getApellidos());
        entity.setNombre(cliente.getNombre());
        entity.setEmail(cliente.getEmail());
        entity.setTipoPersona(cliente.getTipoPersona());
        entity.setTipoDocumento(cliente.getTipoDocumento());
        entity.setRol(cliente.getRol());

        return entity;
    }
}
