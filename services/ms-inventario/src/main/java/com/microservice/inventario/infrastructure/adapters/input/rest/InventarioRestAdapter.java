package com.microservice.inventario.infrastructure.adapters.input.rest;

import com.microservice.inventario.application.ports.input.InventarioServicePort;
import com.microservice.inventario.infrastructure.adapters.input.rest.mapper.InventarioRestMapperManual;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.request.InventarioCreateRequest;
import com.microservice.inventario.shared.response.OperationResult;
import com.microservice.inventario.shared.response.TipoMovimiento;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventario")
@Tag(name = "Inventario", description = "API para la gestión de inventario. Permite registrar stock inicial, manejar stock (entrada/salida), consultar stock actual y validar disponibilidad.")
@SecurityRequirement(name = "bearerAuth")
public class InventarioRestAdapter {

    private final InventarioServicePort servicePort;
    private final InventarioRestMapperManual restMapperManual;

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
                        servicePort.listarInventario()
                                .stream()
                                .map(InventarioRestMapperManual::toInventarioResponse)
                                .toList(),
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
    @PutMapping("/v1/manejo-stock/{productoId}")
    public ResponseEntity<?> manejarStock(
            @PathVariable UUID productoId,
            @RequestParam Integer cantidad,
            @RequestParam TipoMovimiento tipo) {

        servicePort.manejoStock(productoId, cantidad, tipo);

        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        null,
                        "Stock actualizado correctamente",
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

    @GetMapping("/v1/obtenerInventarioCompleto/{id}")
    public ResponseEntity<?> obtenerInventarioConProductoPorId(@PathVariable UUID id) {

        return servicePort.obtenerInventarioPorProductoId(id)
                .map(inventario -> ResponseEntity.ok(
                        OperationResult.isSuccess(
                                InventarioRestMapperManual.toInventarioProductoResponse(inventario),
                                "Inventario con detalles del producto",
                                200
                        )
                ))
                .orElse(((ResponseEntity.notFound().build())));

    }
}
