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
        String name = "Producto Desconocido";
        String category = "General";

        // ASIGNAMOS CATEGORÍAS ÚNICAS PARA EVITAR EL ERROR DEL GRÁFICO
        if (id.endsWith("1")) {
            name = "Cuaderno Universitario";
            category = "Papelería"; // Antes era Útiles
        }
        else if (id.endsWith("2")) {
            name = "Paquete Libros Académicos";
            category = "Libros";
        }
        else if (id.endsWith("3")) {
            name = "Pluma Azul";
            category = "Escritura"; // Antes era Útiles (Esto arregla el duplicado)
        }
        else if (id.endsWith("4")) {
            name = "Mochila Escolar";
            category = "Accesorios";
        }

        ProductDto product = ProductDto.builder()
                .id(id)
                .name(name)
                .currentPrice(new BigDecimal("10.00"))
                .category(new ProductDto.CategoryDto("cat-1", category))
                .build();

        return new OperationResult<>(true, product, "Mock Data", null, null, 200, LocalDate.now());
    }
}
