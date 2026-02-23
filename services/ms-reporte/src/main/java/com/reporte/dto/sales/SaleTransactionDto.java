package com.reporte.dto.sales;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleTransactionDto {
    @JsonProperty("clienteId")
    private String clientId;

    @JsonProperty("total")
    private BigDecimal totalAmount;

    @JsonProperty("fecha")
    private LocalDate date;

    @JsonProperty("estado")
    private String status;

    @JsonProperty("detalles")
    private List<SaleDetailDto> details;
}
