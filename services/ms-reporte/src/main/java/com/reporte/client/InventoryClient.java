package com.reporte.client;

import com.reporte.common.OperationResult;
import com.reporte.dto.inventory.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 8084 como valor seguro por defecto
@FeignClient(name = "ms-inventory", url = "${app.clients.inventory-url:http://localhost:8084}")
public interface InventoryClient {

    @GetMapping("/api/productos/v1/obtener/{id}")
    OperationResult<ProductDto> getProductById(@PathVariable("id") String id);
}