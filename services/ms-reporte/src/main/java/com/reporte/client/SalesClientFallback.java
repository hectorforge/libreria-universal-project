package com.reporte.client;

import com.reporte.common.OperationResult;
import com.reporte.dto.sales.DailySummaryDto;
import com.reporte.dto.sales.SaleDetailDto;
import com.reporte.dto.sales.SaleTransactionDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class SalesClientFallback implements SalesClient {

    @Override
    public OperationResult<List<SaleTransactionDto>> getSalesByDay(String date) {
        return new OperationResult<>(true, new ArrayList<>(), "Mock", null, null, 200, LocalDate.now());
    }

    @Override
    public OperationResult<List<DailySummaryDto>> getSalesSummary(String startDate, String endDate) {
        // DATOS PARA EL GRÁFICO DE BARRAS (6 Meses)
        List<DailySummaryDto> summary = Arrays.asList(
                new DailySummaryDto(LocalDate.of(2025, 8, 1), new BigDecimal("10300.00"), 50),
                new DailySummaryDto(LocalDate.of(2025, 9, 1), new BigDecimal("12438.00"), 65),
                new DailySummaryDto(LocalDate.of(2025, 10, 1), new BigDecimal("14657.00"), 70),
                new DailySummaryDto(LocalDate.of(2025, 11, 1), new BigDecimal("18850.00"), 85),
                new DailySummaryDto(LocalDate.of(2025, 12, 1), new BigDecimal("22000.00"), 100),
                new DailySummaryDto(LocalDate.of(2026, 1, 1), new BigDecimal("15400.00"), 60) // Mes actual
        );
        return new OperationResult<>(true, summary, "Mock Data", null, null, 200, LocalDate.now());
    }

    @Override
    public OperationResult<List<SaleDetailDto>> getSalesDetails(String startDate, String endDate) {
        // DATOS PARA TOP SELLERS Y PIE CHART
        List<SaleDetailDto> details = new ArrayList<>();

        // 1. Cuadernos (Categoría Útiles) - Varios registros para sumar volumen
        details.add(createDetail("prod-1", 2000, "5.00")); // 10,000
        details.add(createDetail("prod-1", 3000, "5.00")); // 15,000

        // 2. Libros (Categoría Libros) - Precio alto, menos unidades
        details.add(createDetail("prod-2", 50, "1200.00")); // 60,000
        details.add(createDetail("prod-2", 50, "1200.00")); // 60,000

        // 3. Lapiceros (Categoría Útiles)
        details.add(createDetail("prod-3", 4200, "1.50")); // 6,300

        // 4. Mochilas (Categoría Otros)
        details.add(createDetail("prod-4", 150, "80.00")); // 12,000

        return new OperationResult<>(true, details, "Mock Data", null, null, 200, LocalDate.now());
    }

    private SaleDetailDto createDetail(String id, int qty, String price) {
        BigDecimal p = new BigDecimal(price);
        return new SaleDetailDto(UUID.fromString("00000000-0000-0000-0000-00000000000" + id.split("-")[1]),
                qty, p, p.multiply(new BigDecimal(qty)));
    }
}
