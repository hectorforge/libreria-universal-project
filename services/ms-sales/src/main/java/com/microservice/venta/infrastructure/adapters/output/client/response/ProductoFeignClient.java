package com.microservice.venta.infrastructure.adapters.output.client.response;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "ms-inventario", url = "http://localhost:8084/api/inventario")
public interface ProductoFeignClient {

    @GetMapping("/v1/obtenerInventarioCompleto/{id}")
    DataResponse<ProductoResponse> obtenerProductoPorId(@PathVariable UUID id);

    @PutMapping("/v1/manejo-stock/{productoId}")
    void actualizarStockProducto(@PathVariable UUID productoId,
                                 @RequestParam Integer cantidad,
                                 @RequestParam String tipo);
}
