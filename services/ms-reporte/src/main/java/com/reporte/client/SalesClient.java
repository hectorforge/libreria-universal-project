package com.reporte.client;

import com.reporte.common.OperationResult;
import com.reporte.dto.sales.SaleTransactionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// 8087 como valor seguro por defecto
@FeignClient(name = "ms-sales", url = "${app.clients.sales-url:http://localhost:8087}")
public interface SalesClient {

    @GetMapping("/api/ventas/v1/listar-fechas")
    OperationResult<List<SaleTransactionDto>> getSalesByDate(
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam("fechaFin") String fechaFin);
}
