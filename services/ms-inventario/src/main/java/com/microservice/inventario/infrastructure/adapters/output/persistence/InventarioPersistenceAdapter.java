package com.microservice.inventario.infrastructure.adapters.output.persistence;

import com.microservice.inventario.application.ports.output.InventarioPersistencePort;
import com.microservice.inventario.domain.model.Inventario;
import com.microservice.inventario.infrastructure.adapters.output.persistence.mapper.InventarioPersistenceMapper;
import com.microservice.inventario.infrastructure.adapters.output.persistence.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InventarioPersistenceAdapter implements InventarioPersistencePort {

    private final InventarioRepository inventarioRepository;
    private final InventarioPersistenceMapper mapper;


    @Override
    public Optional<Inventario> findByProductoId(UUID productoId) {
        return inventarioRepository.findByProductoId(productoId)
                .map(mapper::toInventario);
    }

    @Override
    public Inventario save(Inventario inventario) {
        return mapper.toInventario(
                inventarioRepository.save(
                        mapper.toInventarioEntity(inventario)
                )
        );
    }

    @Override
    public List<Inventario> findAll() {
        return mapper.toInventarioList(inventarioRepository.findAll());
    }
}
