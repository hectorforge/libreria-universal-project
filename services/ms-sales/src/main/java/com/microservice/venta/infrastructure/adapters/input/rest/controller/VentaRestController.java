package com.microservice.venta.infrastructure.adapters.input.rest.controller;

import com.microservice.venta.application.service.VentaService;
import com.microservice.venta.infrastructure.adapters.input.rest.mapper.VentaRestMapper;
import com.microservice.venta.infrastructure.adapters.input.rest.model.request.VentaRequest;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.DniResponse;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.SunatResponse;
import com.microservice.venta.infrastructure.adapters.output.persistence.implementacion.VentaPersistenceAdapter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

import static com.microservice.venta.shared.ErrorCatalog.*;
import static com.microservice.venta.shared.OperationResult.*;
import static com.microservice.venta.shared.ResultCode.*;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "API para la gestión de Ventas. Permite crear, actualizar, consultar, eliminar y listar ventas con filtros y paginación.")
public class VentaRestController {

    private final VentaService service;
    private final VentaRestMapper mapper;

    @Operation(
            summary = "Crear una venta",
            description = "Registra una nueva venta en el sistema. Requiere un cliente válido y detalles de la venta. Devuelve la venta creada o un error si falla la operación.")
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

        return ResponseEntity.status(201).body(isSuccess(
                mapper.toResponse(ventaGuardada), "Venta creada exitosamente", 201));

    }

    @Operation(
            summary = "Obtener una venta",
            description = "Obtiene los detalles de una venta específica utilizando su ID. Devuelve la venta si existe o un error indicando que no se encontró.")
    @GetMapping("/v1/obtener/{id}")
    public ResponseEntity<?> obtenerVentaPorId(@PathVariable UUID id) {
        return service.obtenerVentaPorId(id)
                .map(ventaModel -> ResponseEntity.ok(
                        isSuccess(
                                mapper.toResponse(ventaModel),
                                "Venta obtenida exitosamente",
                                200)
                ))
                .orElseGet(() -> ResponseEntity.status(404).body(
                        failure(catalog(VENTA_NOT_FOUND), 404, VENTA_NOT_FOUND.name())
                ));
    }

    @Operation(
            summary = "Listar ventas",
            description = "Lista todas las ventas registradas en el sistema. Devuelve una lista de ventas o un mensaje indicando que no hay ventas disponibles.")
    @GetMapping("/v1/listar")
    public ResponseEntity<?> listarVentas() {
        return ResponseEntity.ok(isSuccess(
                service.listarVentas().stream().map(mapper::toResponse),
                "Ventas", 200));
    }


    @Operation(
            summary = "Listar ventas por fechas",
            description = "Lista las ventas registradas entre dos fechas específicas. Requiere una fecha de inicio y una fecha de fin. Devuelve una lista de ventas dentro del rango de fechas o un mensaje indicando que no hay ventas disponibles.")
    @GetMapping("/v1/listar-fechas")
    public ResponseEntity<?> listarVentasPorFechas(@RequestParam LocalDate fechaInicio,
                                                  @RequestParam LocalDate fechaFin) {
        return ResponseEntity.ok(isSuccess(
                service.listarVentasPorFechas(fechaInicio, fechaFin).stream().map(mapper::toResponse),
                "Ventas", 200));
    }

    @GetMapping("/sunat/ruc/{numero}")
    public Mono<SunatResponse> consultarRuc(@PathVariable String numero) {
        return service.consultarRuc(numero);
    }

    @GetMapping("/reniec/dni/{numero}")
    public Mono<DniResponse> consultarDni(@PathVariable String numero) {
        return service.consultarDni(numero);
    }

}
