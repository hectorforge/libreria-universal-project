package com.microservice.venta.infrastructure.adapters.input.rest.controller;

import com.microservice.venta.application.service.VentaService;
import com.microservice.venta.infrastructure.adapters.input.rest.mapper.VentaRestMapper;
import com.microservice.venta.infrastructure.adapters.input.rest.model.request.VentaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.microservice.venta.shared.ErrorCatalog.*;
import static com.microservice.venta.shared.OperationResult.*;
import static com.microservice.venta.shared.ResultCode.*;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaRestController {

    private final VentaService service;
    private final VentaRestMapper mapper;

    @PostMapping("/v1/crear")
    public ResponseEntity<?> crear(@RequestBody VentaRequest request) {

        if(request.getClienteId() == null) {
            return ResponseEntity.badRequest().body(validationError(catalog(VENTA_INVALID)));
        }

        var ventaGuardada = service.guardarVenta(mapper.toModel(request));

        if(ventaGuardada == null) {
            return ResponseEntity.status(400).body(
                    failure(catalog(VENTA_NOT_FOUND), 404, VENTA_NOT_FOUND.name())
            );
        }

        return ResponseEntity.status(201).body(success(
                mapper.toResponse(ventaGuardada), "Venta creada exitosamente", 201));

    }

    @GetMapping("/v1/obtener/{id}")
    public ResponseEntity<?> obtenerVentaPorId(@PathVariable UUID id) {
        return service.obtenerVentaPorId(id)
                .map(ventaModel -> ResponseEntity.ok(
                        success(
                                mapper.toResponse(ventaModel),
                                "Venta obtenida exitosamente",
                                200)
                ))
                .orElseGet(() -> ResponseEntity.status(404).body(
                        failure(catalog(VENTA_NOT_FOUND), 404, VENTA_NOT_FOUND.name())
                ));
    }

    @GetMapping("/v1/listar")
    public ResponseEntity<?> listarVentas() {
        return ResponseEntity.ok(success(
                service.listarVentas().stream().map(mapper::toResponse),
                "Ventas", 200));
    }

}
