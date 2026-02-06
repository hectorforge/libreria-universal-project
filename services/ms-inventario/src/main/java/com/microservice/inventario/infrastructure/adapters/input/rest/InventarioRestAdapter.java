package com.microservice.inventario.infrastructure.adapters.input.rest;

import com.microservice.inventario.application.ports.input.InventarioServicePort;
import com.microservice.inventario.infrastructure.adapters.input.rest.mapper.InventarioRestMapper;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.request.InventarioCreateRequest;
import com.microservice.inventario.shared.response.OperationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventario")
public class InventarioRestAdapter {

    private final InventarioServicePort servicePort;
    private final InventarioRestMapper restMapper;

    // Consultar stock actual
    @GetMapping("/v1/obtener/{productoId}")
    public ResponseEntity<?> consultarStock(@PathVariable UUID productoId) {
        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        servicePort.consultarStockProducto(productoId),
                        "Stock actual del producto",
                        200
                )
        );
    }

    @GetMapping("/v1/listar")
    public ResponseEntity<?> listarInventario() {
        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        restMapper.toInventarioResponseList(servicePort.listarInventario()),
                        "Lista completa de inventario",
                        200
                )
        );
    }

    // Registrar stock inicial
    @PostMapping("/v1/registrar")
    public ResponseEntity<?> registrarStock(
            @Valid @RequestBody InventarioCreateRequest request) {

        servicePort.registrarStockInicial(
                request.getProductoId(),
                request.getStockActual()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(
                OperationResult.isSuccess(
                        null,
                        "Stock inicial registrado correctamente",
                        201
                )
        );
    }

    // Manejo de stock (entrada / salida)
    @PutMapping("/v1/api/{productoId}")
    public ResponseEntity<?> manejarStock(
            @PathVariable UUID productoId,
            @RequestParam int cantidad,
            @RequestParam boolean entrada) {

        servicePort.manejoStock(productoId, cantidad, entrada);

        String tipo = entrada ? "entrada" : "salida";
        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        null,
                        "Stock actualizado correctamente (" + tipo + ")",
                        200
                )
        );
    }

    // Validar disponibilidad
    @GetMapping("/v1/api/{productoId}/disponibilidad")
    public ResponseEntity<?> validarDisponibilidad(
            @PathVariable UUID productoId,
            @RequestParam int cantidad) {

        boolean disponible = servicePort.validarDisponibilidadStock(productoId, cantidad);

        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        disponible,
                        "Disponibilidad del producto",
                        200
                )
        );
    }
}
