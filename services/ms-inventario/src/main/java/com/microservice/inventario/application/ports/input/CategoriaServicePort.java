package com.microservice.inventario.application.ports.input;

import com.microservice.inventario.domain.model.Categoria;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaServicePort {

    Categoria registrarCategoria(Categoria categoria);

    Optional<Categoria> actualizarCategoria(UUID id, Categoria categoria);

    void activarCategoria(UUID id);

    Categoria obtenerCategoriaPorId(UUID id);

    List<Categoria> listarCategoria();

    void eliminarCategoria(UUID id);
}
