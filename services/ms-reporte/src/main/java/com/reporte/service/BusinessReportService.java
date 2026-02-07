package com.reporte.service;

import com.reporte.client.ClientClient;
import com.reporte.client.ClientClientFallback;
import com.reporte.client.InventoryClient;
import com.reporte.client.InventoryClientFallback;
import com.reporte.client.SalesClient;
import com.reporte.client.SalesClientFallback;
import com.reporte.common.OperationResult;
import com.reporte.dto.client.ClientDto;
import com.reporte.dto.inventory.ProductDto;
import com.reporte.dto.report.FinancialItem;
import com.reporte.dto.report.TopProductItem;
import com.reporte.dto.report.VipClientItem;
import com.reporte.dto.sales.DailySummaryDto;
import com.reporte.dto.sales.SaleDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BusinessReportService {

    // Clientes Reales (Feign)
    private final SalesClient salesClient;
    private final InventoryClient inventoryClient;
    private final ClientClient clientClient;

    // Mocks de Respaldo (Inyectados explícitamente)
    private final SalesClientFallback salesFallback;
    private final InventoryClientFallback inventoryFallback;
    private final ClientClientFallback clientFallback;

    // 1. Lógica para Reporte de Productos (Top Sellers)
    public List<TopProductItem> getTopSellingProducts(String startDate, String endDate) {
        List<SaleDetailDto> rawSales;

        // INTENTO 1: Llamar al microservicio real
        try {
            OperationResult<List<SaleDetailDto>> result = salesClient.getSalesDetails(startDate, endDate);
            rawSales = result.getData();
        } catch (Exception e) {
            // FALLBACK: Si falla, usar el Mock
            System.out.println("⚠️ Error conectando con ms-sales. Usando Mock.");
            rawSales = salesFallback.getSalesDetails(startDate, endDate).getData();
        }

        if (rawSales == null || rawSales.isEmpty()) return new ArrayList<>();

        // Agrupamiento
        Map<UUID, TopProductItem> aggregationMap = new HashMap<>();
        for (SaleDetailDto sale : rawSales) {
            aggregationMap.putIfAbsent(sale.getProductId(), TopProductItem.builder()
                    .quantitySold(0)
                    .totalRevenue(BigDecimal.ZERO)
                    .build());

            TopProductItem item = aggregationMap.get(sale.getProductId());
            item.setQuantitySold(item.getQuantitySold() + sale.getQuantity());
            item.setTotalRevenue(item.getTotalRevenue().add(sale.getSubtotal()));
        }

        // Ordenamiento
        List<Map.Entry<UUID, TopProductItem>> sortedList = new ArrayList<>(aggregationMap.entrySet());
        sortedList.sort((a, b) -> b.getValue().getQuantitySold().compareTo(a.getValue().getQuantitySold()));

        // Enriquecimiento (Buscar Nombres)
        List<TopProductItem> finalReportList = new ArrayList<>();
        int limit = Math.min(sortedList.size(), 10);

        for (int i = 0; i < limit; i++) {
            UUID productId = sortedList.get(i).getKey();
            TopProductItem item = sortedList.get(i).getValue();

            try {
                // Intentar buscar nombre real
                OperationResult<ProductDto> productResult = inventoryClient.getProductById(productId.toString());
                if (productResult.isSuccess() && productResult.getData() != null) {
                    item.setProductName(productResult.getData().getName());
                    item.setCategoryName(productResult.getData().getCategory().getName());
                }
            } catch (Exception e) {
                // Si falla, usar Mock de Inventario
                ProductDto mockProd = inventoryFallback.getProductById(productId.toString()).getData();
                item.setProductName(mockProd.getName());
                item.setCategoryName(mockProd.getCategory().getName());
            }
            finalReportList.add(item);
        }

        return finalReportList;
    }

    // 2. Lógica para Reporte Financiero (Financial Report)
    public List<FinancialItem> getFinancialData(String startDate, String endDate) {
        List<DailySummaryDto> summaries;

        try {
            OperationResult<List<DailySummaryDto>> result = salesClient.getSalesSummary(startDate, endDate);
            summaries = result.getData();
        } catch (Exception e) {
            System.out.println("⚠️ Error conectando con ms-sales (Summary). Usando Mock.");
            summaries = salesFallback.getSalesSummary(startDate, endDate).getData();
        }

        if (summaries == null) return new ArrayList<>();

        List<FinancialItem> reportItems = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (DailySummaryDto dto : summaries) {
            BigDecimal total = dto.getDailyTotal();
            BigDecimal base = total.divide(new BigDecimal("1.18"), 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal tax = total.subtract(base);

            reportItems.add(FinancialItem.builder()
                    .dateLabel(dto.getDate().format(formatter))
                    .income(total)
                    .net(base)
                    .tax(tax)
                    .build());
        }
        return reportItems;
    }

    // 3. Lógica para Clientes VIP
    public List<VipClientItem> getVipClients(String startDate, String endDate) {
        // Mock rápido de IDs (simulando respuesta de ventas)
        Map<String, BigDecimal> topClientsMock = new LinkedHashMap<>();
        topClientsMock.put("330e8400-e29b-41d4-a716-446655440111", new BigDecimal("15400.00"));
        topClientsMock.put("330e8400-e29b-41d4-a716-446655440222", new BigDecimal("12850.50"));

        List<VipClientItem> vipList = new ArrayList<>();

        for (Map.Entry<String, BigDecimal> entry : topClientsMock.entrySet()) {
            String clientId = entry.getKey();
            BigDecimal total = entry.getValue();

            VipClientItem item = VipClientItem.builder().totalPurchased(total).build();

            try {
                // Intentar buscar cliente real
                OperationResult<ClientDto> clientResult = clientClient.getClientById(clientId);
                if (clientResult.isSuccess() && clientResult.getData() != null) {
                    item.setClientName(clientResult.getData().getBusinessName());
                    item.setEmail(clientResult.getData().getEmail());
                    item.setPhone(clientResult.getData().getPhone());
                }
            } catch (Exception e) {
                // Si falla, usar Mock de Cliente (AQUÍ ESTABA EL ERROR ANTES)
                ClientDto mockClient = clientFallback.getClientById(clientId).getData();
                item.setClientName(mockClient.getBusinessName());
                item.setEmail(mockClient.getEmail());
                item.setPhone(mockClient.getPhone());
            }
            vipList.add(item);
        }
        return vipList;
    }
}
