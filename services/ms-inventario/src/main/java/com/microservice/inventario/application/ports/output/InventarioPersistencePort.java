package com.microservice.inventario.application.ports.output;

import com.microservice.inventario.domain.model.Inventario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventarioPersistencePort {

    Optional<Inventario> findByProductoId(UUID productoId);

    Inventario save(Inventario inventario);

    List<Inventario> findAll();
}