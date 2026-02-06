package com.microservice.inventario.application.ports.output;

import com.microservice.inventario.domain.model.Producto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoPersistencePort {

    Optional<Producto> findById(UUID id);

    List<Producto> findAll();

    List<Producto> findByCategoriaId(UUID categoriaId);

    Producto save(Producto producto);

    void deleteById(UUID id);

}
