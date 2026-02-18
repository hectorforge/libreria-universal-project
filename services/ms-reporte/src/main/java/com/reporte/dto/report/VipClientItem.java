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
public class VipClientItem {
    private String clientName;
    private String email;
    private String phone;
    private BigDecimal totalPurchased;
}
