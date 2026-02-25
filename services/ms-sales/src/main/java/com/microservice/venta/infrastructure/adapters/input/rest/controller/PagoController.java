package com.microservice.venta.infrastructure.adapters.input.rest.controller;

import com.microservice.venta.application.service.PagoService;
import com.microservice.venta.infrastructure.adapters.input.rest.mapper.PagoRestMapper;
import com.microservice.venta.infrastructure.adapters.input.rest.model.request.PagoRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.microservice.venta.shared.ErrorCatalog.*;
import static com.microservice.venta.shared.OperationResult.*;
import static com.microservice.venta.shared.ResultCode.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
@Tag(name = "Pagos", description = "API para la gestión de Pagos. Permite crear, actualizar, consultar, eliminar y listar pagos con filtros y paginación.")
@SecurityRequirement(name = "bearerAuth")
public class PagoController {

    private final PagoService service;
    private final PagoRestMapper mapper;

    @GetMapping("v1/obtener/{id}")
    public ResponseEntity<?> obtenerPagoPorId(@PathVariable Integer id) {
        return service.obtenerPagoPorId(id)
                .map(pago -> ResponseEntity.ok().body(isSuccess(pago, "Pago obtenido exitosamente", 200)))
                .orElseGet(() -> ResponseEntity.status(404).body(failure(catalog(PAGO_NOT_FOUND), 404, PAGO_NOT_FOUND.name())));
    }

    @GetMapping("v1/listar")
    public ResponseEntity<?> listarPagos() {
        return ResponseEntity.ok(
                isSuccess(service.listarPagos()
                        .stream()
                        .map(mapper::toResponse)
                        .toList(),
                        "Pagos listados exitosamente",
                        200)
        );
    }

    public ResponseEntity<?> crearPago() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("v1/actualizar/{id}")
    public ResponseEntity<?> actualizarPago(
             @PathVariable Integer id, @RequestBody PagoRequest request) {

        return ResponseEntity.status(201).body(isSuccess(
                mapper.toResponse(service.actualizarPago(id, PagoRestMapper.toRequest(request))),
                "Pago actualizado exitosamente", 201));
    }

     public ResponseEntity<?> eliminarPago() {
        return ResponseEntity.ok().build();
    }

}
