package com.microservice.venta.infrastructure.adapters.input.rest.controller;

import com.microservice.venta.application.service.FacturacionService;
import com.microservice.venta.infrastructure.adapters.input.rest.mapper.FacturaRestMapper;
import com.microservice.venta.infrastructure.adapters.input.rest.model.request.FacturaRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

import static com.microservice.venta.shared.ErrorCatalog.*;
import static com.microservice.venta.shared.OperationResult.*;
import static com.microservice.venta.shared.ResultCode.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
@Tag(name = "Facturas", description = "API para la gestión de Facturas. Permite crear, actualizar, consultar, eliminar y listar facturas con filtros y paginación.")
@SecurityRequirement(name = "bearerAuth")
public class FacturaRestController {


    private final FacturacionService service;

    @Operation(
            summary = "Generar una factura",
            description = "Registra una nueva factura en el sistema a partir de una venta existente. Requiere el ID de la venta para generar la factura. Devuelve la factura generada o un error si falla la operación.")
    @PostMapping("/v1/generar")
    public ResponseEntity<?> generarFactura(@RequestBody FacturaRequest request) {
        if(request.getIdVenta() == null){
            return ResponseEntity.badRequest().body(validationError(catalog(FACTURACION_INVALID)));
        }

        var facturaGenerada = service.generarFacturacion(FacturaRestMapper.toModel(request));

        if(facturaGenerada == null) {
            return ResponseEntity.status(400).body(
                    failure(catalog(FACTURACION_NOT_FOUND), 404, FACTURACION_NOT_FOUND.name())
            );
        }

        return ResponseEntity.status(201).body(isSuccess(
                FacturaRestMapper.toResponse(facturaGenerada), "Factura generada exitosamente", 201));
    }


    @Operation(
            summary = "Listar facturas",
            description = "Obtiene una lista de todas las facturas registradas en el sistema. Devuelve una lista de facturas o un mensaje indicando que no se encontraron facturas.")
    @GetMapping("/v1/listar")
    public ResponseEntity<?> listarFacturas(){
        return ResponseEntity.ok(isSuccess(
                service.listarFacturaciones().stream().map(FacturaRestMapper::toResponse).toList(),
                "Facturas listadas exitosamente", 200
        ));
    }

    @Operation(
            summary = "Listar facturas por fechas",
            description = "Obtiene una lista de facturas filtradas por un rango de fechas. Requiere las fechas de inicio y fin para el filtro. Devuelve una lista de facturas que se encuentran dentro del rango de fechas o un mensaje indicando que no se encontraron facturas.")
    @GetMapping("/v1/listar/fechas")
    public ResponseEntity<?> listarFacturasPorFechas(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin){
        return ResponseEntity.ok(isSuccess(
                service.listarFacturacionPorFechas(fechaInicio, fechaFin).stream().map(FacturaRestMapper::toResponse).toList(),
                "Facturas listadas por fecha exitosamente", 200
        ));
    }

    @Operation(
            summary = "Obtener una factura",
            description = "Obtiene los detalles de una factura específica utilizando su ID. Devuelve la factura si existe o un error indicando que no se encontró.")
    @GetMapping("/v1/obtener/{id}")
    public ResponseEntity<?> obtenerFacturaPorId(@PathVariable UUID id) {
        return service.obtenerFacturacionPorId(id)
                .map(facturacionModel -> ResponseEntity.ok(
                        isSuccess(
                                FacturaRestMapper.toResponse(facturacionModel),
                                "Factura obtenida exitosamente",
                                200
                        )
                ))
                .orElseGet(() -> ResponseEntity.status(404).body(
                        failure(catalog(FACTURACION_NOT_FOUND), 404, FACTURACION_NOT_FOUND.name())
                ));
    }

    @Operation(
            summary = "Obtener una factura por numero de factura",
            description = "Obtiene los detalles de una factura específica utilizando su numero de factura. Devuelve la factura si existe o un error indicando que no se encontró.")
    @GetMapping("/v1/obtener/numero/{numeroFactura}")
    public ResponseEntity<?> obtenerFacturaPorNumero(@PathVariable String numeroFactura) {
        return service.obtenerFacturacionPorNumero(numeroFactura)
                .map(facturacionModel -> ResponseEntity.ok(
                        isSuccess(
                                FacturaRestMapper.toResponse(facturacionModel),
                                "Factura obtenida exitosamente",
                                200
                        )
                ))
                .orElseGet(() -> ResponseEntity.status(404).body(
                        failure(catalog(FACTURACION_NOT_FOUND), 404, FACTURACION_NOT_FOUND.name())
                ));
    }

    @Operation(
            summary = "Cancelar una factura",
            description = "Cancela una factura existente utilizando su ID. Cambia el estado de la factura a cancelada. Devuelve un mensaje de éxito o un error si falla la operación.")
    @DeleteMapping("/v1/cancelar/{id}")
    public ResponseEntity<?> cancelarFactura(@PathVariable UUID id) {
        service.cancelarFacturacion(id);

        return ResponseEntity.ok(
                isSuccess(null, "Factura cancelada exitosamente", 200)
        );
    }

}
