package com.reporte.client;

import com.reporte.common.OperationResult;
import com.reporte.dto.inventory.ProductDto;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class InventoryClientFallback implements InventoryClient {
    @Override
    public OperationResult<ProductDto> getProductById(String id) {
        // Simulamos resolver nombres según el ID
        String name = "Producto Genérico";
        String category = "General";

        if (id.contains("0001")) { name = "Cuaderno Universitario"; category = "Útiles"; }
        if (id.contains("0002")) { name = "Paquete de Libros Académicos"; category = "Libros"; }

        ProductDto product = ProductDto.builder()
                .id(id)
                .name(name)
                .currentPrice(new BigDecimal("10.00"))
                .category(new ProductDto.CategoryDto("cat-1", category))
                .build();

        return new OperationResult<>(true, product, "Mock Data", null, null, 200, LocalDate.now());
    }
}
