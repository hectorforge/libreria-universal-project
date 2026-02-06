package com.microservice.inventario.infrastructure.adapters.output.persistence;

import com.microservice.inventario.application.ports.output.CategoriaPersistencePort;
import com.microservice.inventario.domain.model.Categoria;
import com.microservice.inventario.infrastructure.adapters.output.persistence.mapper.CategoriaPersistenceMapper;
import com.microservice.inventario.infrastructure.adapters.output.persistence.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoriaPersistenceAdapter implements CategoriaPersistencePort {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaPersistenceMapper mapper;

    @Override
    public Optional<Categoria> findById(UUID id) {
        return categoriaRepository.findById(id)
                .map(mapper::toCategoria);
    }
    @Override
    public List<Categoria> findAll() {
        return mapper.toCategoriaList(categoriaRepository.findAll());
    }
    @Override
    public Categoria save(Categoria categoria) {
        return mapper.toCategoria(
                categoriaRepository.save(
                        mapper.toCategoriaEntity(categoria)
                )
        );
    }
    @Override
    public void deleteById(UUID id) {
        categoriaRepository.deleteById(id);
    }
}
