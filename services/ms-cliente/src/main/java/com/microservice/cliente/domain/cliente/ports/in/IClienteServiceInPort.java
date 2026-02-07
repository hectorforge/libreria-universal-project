package com.microservice.cliente.domain.cliente.ports.in;

import com.libreriauniversal.OperationResult;
import com.libreriauniversal.PagedResult;
import com.microservice.cliente.domain.cliente.Cliente;
import com.microservice.cliente.domain.cliente.utils.ClienteFiltro;

import java.util.UUID;

public interface IClienteServiceInPort {
    /**
     * Crea un nuevo cliente.
     * @param cliente
     * @return OperationResult con el cliente creado o errores de validación
     */
    OperationResult<Cliente> create(Cliente cliente);

    /**
     * Actualiza un cliente existente por su id.
     * @param cliente
     * @return OperationResult con el cliente actualizado o errores
     */
    OperationResult<Cliente> update(Cliente cliente);

    /**
     * Elimina un cliente por su ID.
     * @param id
     * @return OperationResult con el cliente eliminado o error
     */
    OperationResult<Boolean> deleteById(UUID id);

    /**
     * Obtiene un cliente por su ID.
     * @param id
     * @return OperationResult con el cliente encontrado o error
     */
    OperationResult<Cliente> getClienteById(UUID id);

    /**
     * Obtiene todos los clientes y permite filtrar por apellidos, nombres, email y tipo de cliente.
     * @param filtros filtros de búsqueda para los clientes
     * @param page número de página para paginación
     * @param size tamaño de página para paginación
     * @return OperationResult con la lista de clientes o error
     */
    OperationResult<PagedResult<Cliente>> getAllClientes(ClienteFiltro filtros, int page, int size);
}
