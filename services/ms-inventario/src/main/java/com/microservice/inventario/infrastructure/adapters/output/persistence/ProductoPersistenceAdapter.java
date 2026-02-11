package com.microservice.inventario.infrastructure.adapters.output.persistence;

import com.microservice.inventario.application.ports.output.ProductoPersistencePort;
import com.microservice.inventario.application.service.InventarioService;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.input.rest.mapper.ProductoRestMapperManual;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.ProductoInventarioResponse;
import com.microservice.inventario.infrastructure.adapters.output.persistence.mapper.ProductoPersistenceMapperManual;
import com.microservice.inventario.infrastructure.adapters.output.persistence.repository.InventarioRepository;
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
    private final InventarioRepository inventarioRepository;
    private final ProductoPersistenceMapperManual mapperManual;
    private final InventarioService inventarioService;
    private final ProductoRestMapperManual restMapperManual;

    @Override
    public Optional<Producto> findById(UUID id) {
        return repository.findById(id)
                .map(ProductoPersistenceMapperManual::toModel);
    }

    @Override
    public List<Producto> findAll() {
        return repository.listarProductos()
                .stream()
                .map(ProductoPersistenceMapperManual::toModel)
                .toList();
    }

    @Override
    public List<Producto> findByCategoriaId(UUID categoriaId) {
        return repository.findByCategoriaId(categoriaId)
                .stream()
                .map(ProductoPersistenceMapperManual::toModel)
                .toList();
    }

    @Override
    public Producto save(Producto producto) {
        return ProductoPersistenceMapperManual.toModel(
                repository.save(ProductoPersistenceMapperManual.toEntity(producto)
                )
        );
    }
    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<ProductoInventarioResponse> obtenerProductoInventarioPorId(UUID id) {
        return null;
    }

}
