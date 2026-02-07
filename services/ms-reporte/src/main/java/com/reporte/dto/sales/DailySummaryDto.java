package com.reporte.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailySummaryDto {
    private LocalDate date;
    private BigDecimal dailyTotal;
    private Integer transactionCount;
}
