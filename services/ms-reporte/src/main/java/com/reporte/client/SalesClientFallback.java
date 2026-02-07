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
        // Mock data para el reporte diario (Tabla detallada del PDF)
        List<SaleTransactionDto> list = new ArrayList<>();
        list.add(createSale("01/01/2026", new BigDecimal("10800.00")));
        list.add(createSale("01/01/2026", new BigDecimal("10200.00")));

        return new OperationResult<>(true, list, "Mock Data", null, null, 200, LocalDate.now());
    }

    @Override
    public OperationResult<List<DailySummaryDto>> getSalesSummary(String startDate, String endDate) {
        // Mock data para el Gráfico de Barras (Financial Report)
        List<DailySummaryDto> summary = Arrays.asList(
                new DailySummaryDto(LocalDate.parse("2025-08-01"), new BigDecimal("300.00"), 5),
                new DailySummaryDto(LocalDate.parse("2025-09-01"), new BigDecimal("438.00"), 8),
                new DailySummaryDto(LocalDate.parse("2025-10-01"), new BigDecimal("657.00"), 12),
                new DailySummaryDto(LocalDate.parse("2025-11-01"), new BigDecimal("850.00"), 15),
                new DailySummaryDto(LocalDate.parse("2025-12-01"), new BigDecimal("1000.00"), 20),
                new DailySummaryDto(LocalDate.parse("2026-01-01"), new BigDecimal("1205.00"), 25)
        );
        return new OperationResult<>(true, summary, "Mock Data", null, null, 200, LocalDate.now());
    }

    @Override
    public OperationResult<List<SaleDetailDto>> getSalesDetails(String startDate, String endDate) {
        // Mock data para Top Sellers (Simulamos ventas masivas de cuadernos y libros)
        // Retornamos IDs que luego InventoryClient resolverá como "Cuaderno"
        List<SaleDetailDto> details = new ArrayList<>();
        // Producto 1: Cuaderno (5000 unidades) -> ID "prod-1"
        details.add(new SaleDetailDto(UUID.fromString("00000000-0000-0000-0000-000000000001"), 5000, new BigDecimal("5.00"), new BigDecimal("25000")));
        // Producto 2: Libros (S/. 120,000) -> ID "prod-2"
        details.add(new SaleDetailDto(UUID.fromString("00000000-0000-0000-0000-000000000002"), 100, new BigDecimal("1200.00"), new BigDecimal("120000")));

        return new OperationResult<>(true, details, "Mock Data", null, null, 200, LocalDate.now());
    }

    // Helper
    private SaleTransactionDto createSale(String dateStr, BigDecimal total) {
        return SaleTransactionDto.builder()
                .date(LocalDate.now()) // Simplificado para el ejemplo
                .totalAmount(total)
                .status("CONFIRMED")
                .build();
    }
}
