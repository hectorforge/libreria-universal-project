package com.microservice.inventario.application.ports.output;

import com.microservice.inventario.domain.model.Inventario;
import com.microservice.inventario.shared.response.TipoMovimiento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventarioPersistencePort {

    Optional<Inventario> obtenerInventarioPorProductoId(UUID productoId);

    Inventario save(Inventario inventario);

    List<Inventario> findAll();

    void manejoStock(UUID productoId, int cantidad, TipoMovimiento tipo);
}