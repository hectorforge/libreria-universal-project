package com.reporte.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopProductItem {
    private String productName;
    private String categoryName;
    private Integer quantitySold;
    private BigDecimal totalRevenue;
}
