package com.microservice.inventario.application.ports.output;

import com.microservice.inventario.domain.model.Categoria;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaPersistencePort {

    Optional<Categoria> findById(UUID id);

    List<Categoria> findAll();

    Categoria save(Categoria categoria);

    void deleteById(UUID id);
}
