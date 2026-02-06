package com.microservice.inventario.application.service;

import com.microservice.inventario.application.ports.input.CategoriaServicePort;
import com.microservice.inventario.application.ports.output.CategoriaPersistencePort;
import com.microservice.inventario.domain.exception.CategoriaNotFoundException;
import com.microservice.inventario.domain.model.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService implements CategoriaServicePort {

    private final CategoriaPersistencePort categoriaPersistencePort;

     // Registrar categoría
    @Override
    public Categoria registrarCategoria(Categoria categoria) {
        categoria.setEstado(true); // activa por defecto
        return categoriaPersistencePort.save(categoria);
    }


     // Actualizar datos de la categoría
    @Override
    public Optional<Categoria> actualizarCategoria(UUID id, Categoria categoria) {
        return categoriaPersistencePort.findById(id)
                .map(categoriaGuardada -> {
                    categoriaGuardada.setNombre(categoria.getNombre());
                    categoriaGuardada.setDescripcion(categoria.getDescripcion());
                    return categoriaPersistencePort.save(categoriaGuardada);
                });
    }

     // Activar categoría (estado = true)
    @Override
    public void activarCategoria(UUID id) {
        Categoria categoria = categoriaPersistencePort.findById(id)
                .orElseThrow(CategoriaNotFoundException::new);

        categoria.setEstado(true);
        categoriaPersistencePort.save(categoria);
    }

     // Obtener categoría por ID
    @Override
    public Categoria obtenerCategoriaPorId(UUID id) {
        return categoriaPersistencePort.findById(id)
                .orElseThrow(CategoriaNotFoundException::new);
    }

     // Listar todas las categorías
    @Override
    public List<Categoria> listarCategoria() {

        return categoriaPersistencePort.findAll();
    }
    //Elimina
    @Override
    public void eliminarCategoria(UUID id) {
        if (categoriaPersistencePort.findById(id).isEmpty()) {
            throw new CategoriaNotFoundException();
        }
        categoriaPersistencePort.deleteById(id);
    }

    }
