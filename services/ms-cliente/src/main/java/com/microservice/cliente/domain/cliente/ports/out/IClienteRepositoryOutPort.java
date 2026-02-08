package com.microservice.cliente.domain.cliente.ports.out;

import com.libreriauniversal.PagedResult;
import com.microservice.cliente.domain.cliente.Cliente;
import com.microservice.cliente.domain.cliente.utils.ClienteFiltro;

import java.util.Optional;
import java.util.UUID;

public interface IClienteRepositoryOutPort {
    Cliente save(Cliente cliente);
    boolean deleteById(UUID id);
    Optional<Cliente> findById(UUID id);
    Optional<Cliente> findByKeycloakId(String keycloakId);
    PagedResult<Cliente> findAll(ClienteFiltro filtros, int page, int size);
}