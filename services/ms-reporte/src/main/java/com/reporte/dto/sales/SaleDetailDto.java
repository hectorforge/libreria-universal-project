package com.reporte.dto.sales;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailDto {
    @JsonProperty("productoId")
    private UUID productId;

    @JsonProperty("cantidad")
    private Integer quantity;

    @JsonProperty("precioUnitario")
    private BigDecimal unitPrice;

    @JsonProperty("subtotal")
    private BigDecimal subtotal;
}
