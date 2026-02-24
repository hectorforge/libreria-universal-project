package com.microservice.cliente.domain.cliente.ports.in;

import com.libreriauniversal.OperationResult;
import com.libreriauniversal.PagedResult;
import com.microservice.cliente.domain.cliente.Cliente;
import com.microservice.cliente.domain.cliente.utils.ClienteFiltro;

import java.util.UUID;

public interface IClienteServiceInPort {
    OperationResult<Cliente> create(Cliente cliente);
    OperationResult<Cliente> update(UUID id, Cliente cliente);
    OperationResult<Boolean> deleteById(UUID id);
    OperationResult<Cliente> getClienteById(UUID id);
    OperationResult<PagedResult<Cliente>> getAllClientes(ClienteFiltro filtros, int page, int size);
    OperationResult<Cliente> syncWithKeycloak(Cliente cliente);
    OperationResult<Cliente> findByKeycloakId(String keycloakId);
}
