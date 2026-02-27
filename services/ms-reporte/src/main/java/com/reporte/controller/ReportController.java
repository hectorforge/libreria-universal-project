package com.reporte.controller;

import com.reporte.service.BusinessReportService;
import com.reporte.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final BusinessReportService businessReportService;
    private final ReportService reportService;

    // 1. Reporte Financiero de Ventas [cite: 210]
    @GetMapping("/sales/performance")
    public ResponseEntity<byte[]> getSalesPerformanceReport(
            @RequestParam(required = false, defaultValue = "MONTHLY") String type,
            @RequestParam String startDate,
            @RequestParam String endDate) throws Exception {

        // Obtener datos procesados
        List<?> data = businessReportService.getFinancialData(startDate, endDate);

        // Parámetros para el título del PDF
        Map<String, Object> params = new HashMap<>();
        params.put("reportTitle", "Reporte Financiero de Ventas");
        params.put("dateRange", "Del " + startDate + " al " + endDate);

        // Generar PDF
        byte[] pdfBytes = reportService.generatePdfReport("financial_report", params, data);

        return createPdfResponse(pdfBytes, "financial_report.pdf");
    }

    // 2. Reporte de Top Sellers [cite: 232]
    @GetMapping("/products/top-sellers")
    public ResponseEntity<byte[]> getTopSellersReport(
            @RequestParam String startDate,
            @RequestParam String endDate) throws Exception {

        List<?> data = businessReportService.getTopSellingProducts(startDate, endDate);

        Map<String, Object> params = new HashMap<>();
        params.put("reportTitle", "Top Sellers y Tendencias");

        byte[] pdfBytes = reportService.generatePdfReport("top_products", params, data);

        return createPdfResponse(pdfBytes, "top_sellers.pdf");
    }

    // 3. Reporte de Clientes VIP [cite: 241]
    @GetMapping("/clients/vip")
    public ResponseEntity<byte[]> getVipClientsReport(
            @RequestParam String startDate,
            @RequestParam String endDate) throws Exception {

        List<?> data = businessReportService.getVipClients(startDate, endDate);

        Map<String, Object> params = new HashMap<>();
        params.put("reportTitle", "Clientes VIP - Top Compradores");

        byte[] pdfBytes = reportService.generatePdfReport("vip_clients", params, data);

        return createPdfResponse(pdfBytes, "vip_clients.pdf");
    }

    // Método auxiliar para configurar los Headers de descarga
    private ResponseEntity<byte[]> createPdfResponse(byte[] content, String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return ResponseEntity.ok()
                .headers(headers)
                .body(content);
    }
}
