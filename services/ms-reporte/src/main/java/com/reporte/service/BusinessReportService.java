package com.reporte.service;

import com.reporte.client.ClientDummyRepository;
import com.reporte.client.InventoryClient;
import com.reporte.client.SalesClient;
import com.reporte.common.OperationResult;
import com.reporte.dto.client.ClientDto;
import com.reporte.dto.inventory.ProductDto;
import com.reporte.dto.report.FinancialItem;
import com.reporte.dto.report.TopProductItem;
import com.reporte.dto.report.VipClientItem;
import com.reporte.dto.sales.SaleDetailDto;
import com.reporte.dto.sales.SaleTransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BusinessReportService {

    private final SalesClient salesClient;
    private final InventoryClient inventoryClient;
    private final ClientDummyRepository clientDummyRepository;

    // 1. TOP SELLERS
    public List<TopProductItem> getTopSellingProducts(String startDate, String endDate) {
        List<SaleTransactionDto> allSales = fetchSales(startDate, endDate);
        if (allSales.isEmpty()) return new ArrayList<>();

        Map<UUID, TopProductItem> aggregationMap = new HashMap<>();

        for (SaleTransactionDto sale : allSales) {
            if (sale.getDetails() == null) continue;
            for (SaleDetailDto detail : sale.getDetails()) {
                aggregationMap.putIfAbsent(detail.getProductId(), TopProductItem.builder()
                        .quantitySold(0)
                        .totalRevenue(BigDecimal.ZERO)
                        .build());

                TopProductItem item = aggregationMap.get(detail.getProductId());
                item.setQuantitySold(item.getQuantitySold() + detail.getQuantity());
                item.setTotalRevenue(item.getTotalRevenue().add(detail.getSubtotal()));
            }
        }

        List<Map.Entry<UUID, TopProductItem>> sortedList = new ArrayList<>(aggregationMap.entrySet());
        sortedList.sort((a, b) -> b.getValue().getQuantitySold().compareTo(a.getValue().getQuantitySold()));

        List<TopProductItem> finalReportList = new ArrayList<>();
        int limit = Math.min(sortedList.size(), 10);

        for (int i = 0; i < limit; i++) {
            UUID productId = sortedList.get(i).getKey();
            TopProductItem item = sortedList.get(i).getValue();

            try {
                OperationResult<ProductDto> productResult = inventoryClient.getProductById(productId.toString());
                if (productResult != null && productResult.getData() != null) {
                    item.setProductName(productResult.getData().getName());
                    item.setCategoryName(productResult.getData().getCategory() != null ? productResult.getData().getCategory().getName() : "Sin Categoría");
                } else {
                    item.setProductName("Producto " + productId.toString().substring(0, 5));
                    item.setCategoryName("Otros");
                }
            } catch (Exception e) {
                item.setProductName("Producto " + productId.toString().substring(0, 5));
                item.setCategoryName("Otros");
            }
            finalReportList.add(item);
        }
        return finalReportList;
    }

    // 2. FINANCIAL REPORT (Agrupación dinámica)
    public List<FinancialItem> getFinancialData(String startDate, String endDate) {
        List<SaleTransactionDto> allSales = fetchSales(startDate, endDate);
        if (allSales.isEmpty()) return new ArrayList<>();

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        long daysBetween = ChronoUnit.DAYS.between(start, end);

        // TreeMap mantiene el orden cronológico
        Map<String, BigDecimal> groupedSales = new TreeMap<>();

        for (SaleTransactionDto sale : allSales) {
            String label;
            if (daysBetween <= 31) {
                label = sale.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } else {
                label = sale.getDate().format(DateTimeFormatter.ofPattern("MM/yyyy"));
            }
            groupedSales.merge(label, sale.getTotalAmount(), BigDecimal::add);
        }

        List<FinancialItem> reportItems = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> entry : groupedSales.entrySet()) {
            BigDecimal total = entry.getValue();
            BigDecimal base = total.divide(new BigDecimal("1.18"), 2, java.math.RoundingMode.HALF_UP);
            BigDecimal tax = total.subtract(base);

            reportItems.add(FinancialItem.builder()
                    .dateLabel(entry.getKey())
                    .income(total)
                    .net(base)
                    .tax(tax)
                    .build());
        }
        return reportItems;
    }

    // 3. VIP CLIENTS
    public List<VipClientItem> getVipClients(String startDate, String endDate) {
        List<SaleTransactionDto> allSales = fetchSales(startDate, endDate);
        if (allSales.isEmpty()) return new ArrayList<>();

        Map<String, BigDecimal> clientMap = new HashMap<>();

        for (SaleTransactionDto sale : allSales) {
            if (sale.getClientId() != null) {
                clientMap.merge(sale.getClientId(), sale.getTotalAmount(), BigDecimal::add);
            }
        }

        List<Map.Entry<String, BigDecimal>> sortedClients = new ArrayList<>(clientMap.entrySet());
        sortedClients.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        List<VipClientItem> vipList = new ArrayList<>();
        int limit = Math.min(sortedClients.size(), 10);

        for (int i = 0; i < limit; i++) {
            String clientId = sortedClients.get(i).getKey();
            BigDecimal total = sortedClients.get(i).getValue();

            // Usamos el Dummy Repository para evitar problemas con Token/Seguridad
            ClientDto clientData = clientDummyRepository.getClientById(clientId);

            vipList.add(VipClientItem.builder()
                    .clientName(clientData.getBusinessName())
                    .email(clientData.getEmail())
                    .totalPurchased(total)
                    .build());
        }
        return vipList;
    }

    // Método helper para llamar a Ventas y evitar crasheos si está apagado
    private List<SaleTransactionDto> fetchSales(String startDate, String endDate) {
        try {
            OperationResult<List<SaleTransactionDto>> result = salesClient.getSalesByDate(startDate, endDate);
            if (result != null && result.getData() != null) {
                return result.getData();
            }
        } catch (Exception e) {
            System.err.println("⚠️ ms-sales no responde o error al mapear: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
