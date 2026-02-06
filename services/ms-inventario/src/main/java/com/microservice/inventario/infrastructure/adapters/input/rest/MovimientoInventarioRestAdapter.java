package com.microservice.inventario.infrastructure.adapters.input.rest;

import com.microservice.inventario.application.ports.input.MovimientoServicePort;
import com.microservice.inventario.domain.model.MovimientoInventario;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.input.rest.mapper.MovimientoInventarioRestMapper;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.request.MovimientoInventarioCreateRequest;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.MovimientoInventarioResponse;
import com.microservice.inventario.shared.response.OperationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movimientos")
public class MovimientoInventarioRestAdapter {

    private final MovimientoServicePort servicePort;
    private final MovimientoInventarioRestMapper restMapper;

    // Registrar movimiento
    @PostMapping("/v1/registrar")
    public ResponseEntity<?> registrarMovimiento(
            @Valid @RequestBody MovimientoInventarioCreateRequest request) {

        MovimientoInventario movimiento = MovimientoInventario.builder()
                .producto(Producto.builder().id(request.getProductoId()).build())
                .tipo(MovimientoInventario.TipoMovimiento.valueOf(request.getTipo().name()))
                .cantidad(request.getCantidad())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(
                OperationResult.isSuccess(
                        restMapper.toMovimientoResponse(
                                servicePort.registrarMovimiento(movimiento)
                        ),
                        "Movimiento registrado correctamente",
                        201
                )
        );
    }

    // Listar todos
    @GetMapping("/v1/listar")
    public ResponseEntity<?> listar() {
        List<MovimientoInventarioResponse> movimientos =
                restMapper.toMovimientoResponseList(servicePort.listarMovimientos());

        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        movimientos,
                        "Lista de movimientos de inventario",
                        200
                )
        );
    }

    // Listar por producto
    @GetMapping("/v1/producto/{productoId}")
    public ResponseEntity<?> listarPorProducto(@PathVariable UUID productoId) {
        List<MovimientoInventarioResponse> movimientos =
                restMapper.toMovimientoResponseList(servicePort.listarMovimientosPorProducto(productoId));

        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        movimientos,
                        "Movimientos del producto",
                        200
                )
        );
    }
}
