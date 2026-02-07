package com.reporte.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinancialItem {
    private String dateLabel; // "01/01/2026" o "Enero 2026"
    private BigDecimal income; // Ingresos
    private BigDecimal tax;    // Impuestos
    private BigDecimal net;    // Neto
}
