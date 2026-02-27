package com.reporte.client;

import com.reporte.common.OperationResult;
import com.reporte.dto.sales.DailySummaryDto;
import com.reporte.dto.sales.SaleDetailDto;
import com.reporte.dto.sales.SaleTransactionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-sales", fallback = SalesClientFallback.class)
public interface SalesClient {

    @GetMapping("/api/v1/sales/by-day/{date}")
    OperationResult<List<SaleTransactionDto>> getSalesByDay(@PathVariable("date") String date);

    @GetMapping("/api/v1/sales/summary")
    OperationResult<List<DailySummaryDto>> getSalesSummary(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate);

    @GetMapping("/api/v1/sales/details")
    OperationResult<List<SaleDetailDto>> getSalesDetails(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate);
}
