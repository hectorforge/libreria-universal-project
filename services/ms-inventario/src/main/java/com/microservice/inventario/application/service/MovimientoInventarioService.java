package com.microservice.inventario.application.service;

import com.microservice.inventario.application.ports.input.MovimientoServicePort;
import com.microservice.inventario.application.ports.output.MovimientoInventarioPersistencePort;
import com.microservice.inventario.domain.model.MovimientoInventario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovimientoInventarioService implements MovimientoServicePort {

    private final MovimientoInventarioPersistencePort persistencePort;

     // Registrar movimiento de inventario
    @Override
    public MovimientoInventario registrarMovimiento(MovimientoInventario movimiento) {
        movimiento.setFecha(LocalDateTime.now());
        return persistencePort.save(movimiento);
    }

     // Listar todos los movimientos
    @Override
    public List<MovimientoInventario> listarMovimientos() {
        return persistencePort.findAll();
    }

     // Listar movimientos por producto
    @Override
    public List<MovimientoInventario> listarMovimientosPorProducto(UUID productoId) {
        return persistencePort.findByProductoId(productoId);
    }

     //Listar movimientos por rango de fechas
    @Override
    public List<MovimientoInventario> listarMovimientosPorFecha(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {

        return persistencePort.findByFechaBetween(fechaInicio, fechaFin);
    }
}
