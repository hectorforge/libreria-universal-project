package com.microservice.venta.infrastructure.adapters.input.rest.controller;

import com.microservice.venta.application.service.VentaService;
import com.microservice.venta.infrastructure.adapters.input.rest.mapper.DetalleVentaRestMapper;
import com.microservice.venta.infrastructure.adapters.input.rest.mapper.VentaRestMapper;
import com.microservice.venta.infrastructure.adapters.output.client.response.ProductoFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.microservice.venta.shared.OperationResult.isSuccess;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ventas-feign")
@RequiredArgsConstructor
@Tag(name = "Feing", description = "API para probar la comunicación entre microservicios utilizando Feign Client. Permite obtener detalles de productos desde el microservicio de inventario para verificar la integración y el funcionamiento correcto de Feign Client.")
@SecurityRequirement(name = "bearerAuth")
public class FeingController {

    private final ProductoFeignClient productoFeignClient;
    private final VentaRestMapper ventaRestMapper;
    private final VentaService ventaService;

    @Operation(
            summary = "Obtener una venta",
            description = "Obtiene los detalles de una venta específica utilizando su ID. Devuelve la venta si existe o un error indicando que no se encontró.")
    @GetMapping("/v1/obtener/producto/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(productoFeignClient.obtenerProductoPorId(id));
    } //c1d2e3f4-0003-0003-0003-000000000001

    @Operation(
            summary = "Actualizar stock de un producto",
            description = "Actualiza el stock de un producto específico utilizando su ID, la cantidad a ajustar y el tipo de movimiento (entrada o salida). Devuelve una respuesta vacía indicando que la operación se realizó correctamente.")
    @PutMapping("/v1/api/manejo-stock/{productoId}")
    public ResponseEntity<String> actualizarStockProducto(
            @PathVariable UUID productoId,
            @RequestParam Integer cantidad) {

        productoFeignClient.actualizarStockProducto(productoId, cantidad, "SALIDA");

        return ResponseEntity.ok("Stock actualizado correctamente");
    }

//    @Operation(
//            summary = "Listar ventas con Productos completos",
//            description = "Obtiene una lista de ventas con detalles completos de los productos utilizando Feign Client para comunicarse con el microservicio de inventario. Devuelve una lista de ventas con información detallada de cada producto incluido en la venta.")
//    @GetMapping("/v1/listar")
//    public ResponseEntity<?> listarVentasCompletas() {
//        return ResponseEntity.ok(isSuccess(ventaService.listarVentasCompletasFeign(), "Ventas", 200));
//    }

    @Operation(
            summary = "Listar ventas",
            description = "Lista todas las ventas registradas en el sistema. Devuelve una lista de ventas o un mensaje indicando que no hay ventas disponibles.")
    @GetMapping("/v1/listar-ventas-feign")
    public ResponseEntity<?> listarVentas() {
        return ResponseEntity.ok(isSuccess(
                ventaService.listarVentas().stream().map(ventaRestMapper::toVentaCompletaFeign),
                "Ventas", 200));
    }
}
