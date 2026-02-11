package com.microservice.inventario.infrastructure.adapters.input.rest;

import com.microservice.inventario.application.ports.input.CategoriaServicePort;
import com.microservice.inventario.infrastructure.adapters.input.rest.mapper.CategoriaResponseMapperManual;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.request.CategoriaCreateRequest;
import com.microservice.inventario.shared.response.OperationResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categoria")
@Tag(name = "Categoria", description = "API para la gestión de categorías. Permite crear, actualizar, consultar, eliminar y listar categorías.")
public class CategoriaRestAdapter {

    private final CategoriaServicePort servicePort;

    @GetMapping("/v1/listar")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        servicePort.listarCategoria()
                                .stream()
                                .map(CategoriaResponseMapperManual::toCategoriaResponse)
                                .toList(),
                        "Lista de categorías",
                        200
                )
        );
    }

    @GetMapping("/v1/obtener/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        CategoriaResponseMapperManual.toCategoriaResponse(
                                servicePort.obtenerCategoriaPorId(id)
                        ),
                        "Categoría obtenida",
                        200
                )
        );
    }


    @PostMapping("/v1/registrar")
    public ResponseEntity<?> save(@Valid @RequestBody CategoriaCreateRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                OperationResult.isSuccess(
                        CategoriaResponseMapperManual.toCategoriaResponse(
                                servicePort.registrarCategoria(
                                        CategoriaResponseMapperManual.toModel(request)
                                )
                        ),
                        "Categoría creada exitosamente",
                        201
                )
        );
    }

    @PutMapping("/v1/api/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @Valid @RequestBody CategoriaCreateRequest request
    ) {
        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        CategoriaResponseMapperManual.toCategoriaResponse(
                                servicePort.actualizarCategoria(
                                        id,
                                        CategoriaResponseMapperManual.toModel(request)
                                ).orElseThrow(() ->
                                        new RuntimeException("Categoría no encontrada"))
                        ),
                        "Categoría actualizada correctamente",
                        200
                )
        );
    }

    @PatchMapping("/v1/api/{id}/activar")
    public ResponseEntity<?> activar(@PathVariable UUID id) {
        servicePort.activarCategoria(id);

        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        null,
                        "Categoría activada correctamente",
                        200
                )
        );
    }

    @DeleteMapping("/v1/api/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        servicePort.eliminarCategoria(id);

        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        null,
                        "Categoría eliminada correctamente",
                        200
                )
        );
    }
}
