package com.microservice.cliente.domain.cliente.ports.out;

import com.microservice.cliente.domain.cliente.Cliente;
import com.microservice.cliente.domain.cliente.utils.ClienteFiltro;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IClienteRepositoryOutPort {
    /**
     * Guarda un nuevo cliente o actualiza uno existente.
     * @param cliente
     * @return El cliente guardado
     */
    Cliente save(Cliente cliente);

    /**
     * Elimina un cliente por su ID.
     * @param id
     * @return true si se eliminó, false si no existía
     */
    boolean deleteById(UUID id);

    /**
     * Busca un cliente por su ID.
     * @param id
     * @return Optional con el cliente si existe, vacío si no
     */
    Optional<Cliente> findById(UUID id);

    /**
     * Obtiene todos los clientes.
     * @param filtro Filtros de búsqueda para los clientes
     * @param page Número de página para paginación
     * @param size Tamaño de página para paginación
     * @return Lista de clientes
     */
    List<Cliente> findAll(ClienteFiltro filtro, int page, int size);
}
