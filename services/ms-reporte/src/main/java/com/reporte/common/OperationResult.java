package com.reporte.common;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationResult<T> {

    // Esto asegura que lea el JSON si lo mandan como "success" o "isSuccess"
    @JsonAlias({"success", "isSuccess"})
    private boolean isSuccess;

    private T data;
    private String message;
    private Map<String, String> errors;
    private String errorCode;
    private int statusCode;
    private LocalDate timestamp;
}
