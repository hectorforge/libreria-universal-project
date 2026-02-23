package com.reporte.dto.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("categoria")
    private CategoryDto category;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryDto {
        @JsonProperty("nombre")
        private String name;
    }
}
