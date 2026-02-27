package com.reporte.client;

import com.reporte.common.OperationResult;
import com.reporte.dto.inventory.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-inventory", fallback = InventoryClientFallback.class)
public interface InventoryClient {
    @GetMapping("/api/v1/products/{id}")
    OperationResult<ProductDto> getProductById(@PathVariable("id") String id);
}
