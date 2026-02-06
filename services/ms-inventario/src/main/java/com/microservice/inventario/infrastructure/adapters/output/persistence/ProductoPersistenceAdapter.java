package com.microservice.inventario.infrastructure.adapters.output.persistence;

import com.microservice.inventario.application.ports.output.ProductoPersistencePort;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.output.persistence.mapper.ProductoPersistenceMapper;
import com.microservice.inventario.infrastructure.adapters.output.persistence.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductoPersistenceAdapter implements ProductoPersistencePort { // aca implementara el adapter de puerto de salida

    private final ProductoRepository repository;
    private final ProductoPersistenceMapper mapper;

    @Override
    public Optional<Producto> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toProducto);
    }

    @Override
    public List<Producto> findAll() {

        return mapper.toProductoList(repository.listarProductos());
    }

    @Override
    public List<Producto> findByCategoriaId(UUID categoriaId) {
        return mapper.toProductoList(
                repository.findByCategoriaId(categoriaId)
        );
    }

    @Override
    public Producto save(Producto producto) {
        return mapper.toProducto(
                repository.save(mapper.toProductoEntity(producto)
                )
        );
    }
    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
