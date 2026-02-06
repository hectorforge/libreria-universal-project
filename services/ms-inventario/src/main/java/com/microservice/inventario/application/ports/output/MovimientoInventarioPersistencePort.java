package com.microservice.inventario.application.ports.output;

import com.microservice.inventario.domain.model.MovimientoInventario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovimientoInventarioPersistencePort {

    Optional<MovimientoInventario> findById(UUID id);

    List<MovimientoInventario> findAll();

    List<MovimientoInventario> findByProductoId(UUID productoId);

    List<MovimientoInventario> findByFechaBetween(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    );

    MovimientoInventario save(MovimientoInventario movimiento);
}
