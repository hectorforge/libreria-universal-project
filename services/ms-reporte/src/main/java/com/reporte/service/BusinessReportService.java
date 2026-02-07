package com.reporte.service;

import com.reporte.client.ClientClient;
import com.reporte.client.InventoryClient;
import com.reporte.client.SalesClient;
import com.reporte.common.OperationResult;
import com.reporte.dto.client.ClientDto;
import com.reporte.dto.inventory.ProductDto;
import com.reporte.dto.report.FinancialItem;
import com.reporte.dto.report.TopProductItem;
import com.reporte.dto.report.VipClientItem;
import com.reporte.dto.sales.DailySummaryDto;
import com.reporte.dto.sales.SaleDetailDto;
import com.reporte.dto.sales.SaleTransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusinessReportService {

    private final SalesClient salesClient;
    private final InventoryClient inventoryClient;
    private final ClientClient clientClient;

    // 1. Lógica para Reporte de Productos (Top Sellers)
    public List<TopProductItem> getTopSellingProducts(String startDate, String endDate) {
        // A. Traer datos crudos de ventas (IDs y Cantidades)
        // Nota: El mock nos devolverá la lista que definimos en Fase 2
        OperationResult<List<SaleDetailDto>> salesResult = salesClient.getSalesDetails(startDate, endDate);
        List<SaleDetailDto> rawSales = salesResult.getData();

        if (rawSales == null || rawSales.isEmpty()) return new ArrayList<>();

        // B. Agrupar por Producto ID (Sumar cantidades y totales)
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

        // C. Convertir a lista y ordenar (Mayor cantidad primero)
        List<Map.Entry<UUID, TopProductItem>> sortedList = new ArrayList<>(aggregationMap.entrySet());
        sortedList.sort((a, b) -> b.getValue().getQuantitySold().compareTo(a.getValue().getQuantitySold()));

        // D. Top 10 y Enriquecimiento (Buscar nombres en ms-inventory)
        List<TopProductItem> finalReportList = new ArrayList<>();
        int limit = Math.min(sortedList.size(), 10); // Máximo 10 [cite: 131]

        for (int i = 0; i < limit; i++) {
            UUID productId = sortedList.get(i).getKey();
            TopProductItem item = sortedList.get(i).getValue();

            // Llamada a ms-inventory para obtener el nombre real
            try {
                OperationResult<ProductDto> productResult = inventoryClient.getProductById(productId.toString());
                if (productResult.isSuccess() && productResult.getData() != null) {
                    item.setProductName(productResult.getData().getName());
                    item.setCategoryName(productResult.getData().getCategory().getName());
                } else {
                    item.setProductName("Producto Desconocido (" + productId + ")");
                    item.setCategoryName("N/A");
                }
            } catch (Exception e) {
                item.setProductName("Error recuperando nombre");
                item.setCategoryName("Error");
            }
            finalReportList.add(item);
        }

        return finalReportList;
    }

    // 2. Lógica para Reporte Financiero (Financial Report)
    public List<FinancialItem> getFinancialData(String startDate, String endDate) {
        // Usamos el endpoint de resumen (/summary) que es más eficiente [cite: 16]
        OperationResult<List<DailySummaryDto>> result = salesClient.getSalesSummary(startDate, endDate);
        List<DailySummaryDto> summaries = result.getData();

        if (summaries == null) return new ArrayList<>();

        List<FinancialItem> reportItems = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (DailySummaryDto dto : summaries) {
            // Simulamos cálculo de impuestos (IGV 18% en Perú aprox, o extraído del total)
            BigDecimal total = dto.getDailyTotal();
            BigDecimal base = total.divide(new BigDecimal("1.18"), 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal tax = total.subtract(base);

            reportItems.add(FinancialItem.builder()
                    .dateLabel(dto.getDate().format(formatter))
                    .income(total) // Total Bruto (con impuestos)
                    .net(base)     // Valor Venta (sin impuestos)
                    .tax(tax)      // IGV
                    .build());
        }
        return reportItems;
    }

    // 3. Lógica para Clientes VIP
    public List<VipClientItem> getVipClients(String startDate, String endDate) {
        // En un escenario real, agruparíamos transacciones por clienteID.
        // Para este MVP con mocks, simularemos la respuesta directa llamando al cliente.
        // Asumimos que tenemos una lista de IDs de clientes con sus montos totales.

        List<VipClientItem> vipList = new ArrayList<>();

        // Mock rápido de IDs (En real vendría de salesClient.getSalesDetails con grouping)
        Map<String, BigDecimal> topClientsMock = new LinkedHashMap<>();
        topClientsMock.put("330e8400-e29b-41d4-a716-446655440111", new BigDecimal("15400.00"));
        topClientsMock.put("330e8400-e29b-41d4-a716-446655440222", new BigDecimal("12850.50"));

        for (Map.Entry<String, BigDecimal> entry : topClientsMock.entrySet()) {
            String clientId = entry.getKey();
            BigDecimal total = entry.getValue();

            // Data Enrichment: Buscar info personal del cliente [cite: 314]
            VipClientItem item = VipClientItem.builder().totalPurchased(total).build();
            try {
                OperationResult<ClientDto> clientResult = clientClient.getClientById(clientId);
                if (clientResult.isSuccess() && clientResult.getData() != null) {
                    item.setClientName(clientResult.getData().getBusinessName());
                    item.setEmail(clientResult.getData().getEmail());
                    item.setPhone(clientResult.getData().getPhone());
                } else {
                    item.setClientName("Cliente ID: " + clientId);
                }
            } catch (Exception e) {
                item.setClientName("Error Cliente");
            }
            vipList.add(item);
        }
        return vipList;
    }
}
