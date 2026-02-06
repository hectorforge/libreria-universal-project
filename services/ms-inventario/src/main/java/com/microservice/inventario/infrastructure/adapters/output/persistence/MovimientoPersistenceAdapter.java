package com.microservice.inventario.infrastructure.adapters.output.persistence;

import com.microservice.inventario.application.ports.output.MovimientoInventarioPersistencePort;
import com.microservice.inventario.domain.model.MovimientoInventario;
import com.microservice.inventario.infrastructure.adapters.output.persistence.mapper.MovimientoInventarioPersistenceMapper;
import com.microservice.inventario.infrastructure.adapters.output.persistence.repository.MovimientoInventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class MovimientoPersistenceAdapter implements MovimientoInventarioPersistencePort {

    private final MovimientoInventarioRepository repository;
    private final MovimientoInventarioPersistenceMapper mapper;

    @Override
    public Optional<MovimientoInventario> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toMovimiento);
    }

    //  LISTAR TODOS (CON PRODUCTO)
    @Override
    public List<MovimientoInventario> findAll() {
        return mapper.toMovimientoList(
                repository.listarTodosConProducto()
        );
    }

    // LISTAR POR PRODUCTO (CON PRODUCTO)
    @Override
    public List<MovimientoInventario> findByProductoId(UUID productoId) {
        return mapper.toMovimientoList(
                repository.listarPorProductoIdConProducto(productoId)
        );
    }

    @Override
    public List<MovimientoInventario> findByFechaBetween(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {

        return mapper.toMovimientoList(
                repository.findByFechaBetween(fechaInicio, fechaFin)
        );
    }

    @Override
    public MovimientoInventario save(MovimientoInventario movimiento) {
        return mapper.toMovimiento(
                repository.save(
                        mapper.toMovimientoEntity(movimiento)
                )
        );
    }
}