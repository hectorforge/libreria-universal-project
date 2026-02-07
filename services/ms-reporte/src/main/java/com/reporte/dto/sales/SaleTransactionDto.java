package com.reporte.dto.sales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleTransactionDto {
    private UUID id;
    private String code;
    private UUID clientId;
    private BigDecimal totalAmount;
    private LocalDate date;
    private String status;
    private List<SaleDetailDto> details;
}
