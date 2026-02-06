package com.microservice.inventario.application.ports.input;

import com.microservice.inventario.domain.model.MovimientoInventario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface MovimientoServicePort {

    MovimientoInventario registrarMovimiento(MovimientoInventario movimiento);

    List<MovimientoInventario> listarMovimientos();

    List<MovimientoInventario> listarMovimientosPorProducto(UUID productoId);

    List<MovimientoInventario> listarMovimientosPorFecha(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    );
}
