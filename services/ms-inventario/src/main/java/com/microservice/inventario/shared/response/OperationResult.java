package com.microservice.inventario.shared.response;

import com.microservice.inventario.shared.response.pagination.PaginaResult;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class OperationResult<T> {


    private boolean isSuccess; // ← IMPORTANTE
    private T data;
    private String message;
    private Map<String, String> errors;
    private String errorCode;
    private int statusCode;
    private LocalDate timestamp;

    private OperationResult() {
        this.timestamp = LocalDate.now();
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    // ================= SUCCESS =================
    public static <T> OperationResult<T> isSuccess(T data, String message, int status) {
        OperationResult<T> result = new OperationResult<>();
        result.isSuccess = true;
        result.data = data;
        result.message = message;
        result.statusCode = status;
        return result;
    }

    public static <T> OperationResult<T> successWithoutData(String message, int status) {
        OperationResult<T> result = new OperationResult<>();
        result.isSuccess = true;
        result.message = message;
        result.statusCode = status;
        return result;
    }

    // ================= VALIDATION =================
    public static <T> OperationResult<T> validationError(String error) {
        OperationResult<T> result = new OperationResult<>();
        result.isSuccess = false;
        result.message = "Error de validación";
        result.statusCode = 400;

        Map<String, String> errors = new HashMap<>();
        errors.put("error", error);
        result.errors = errors;

        return result;
    }

    // ================= FAILURE =================
    public static <T> OperationResult<T> failure(String message, int status, String errorCode) {
        OperationResult<T> result = new OperationResult<>();
        result.isSuccess = false;
        result.message = message;
        result.statusCode = status;
        result.errorCode = errorCode;
        return result;
    }

    // ================= PAGINACIONES =================

    public static <T> OperationResult<PaginaResult<T>> successPagination(
            PaginaResult<T> pagina,
            String message,
            int status
    ) {
        OperationResult<PaginaResult<T>> result = new OperationResult<>();
        result.isSuccess = true;
        result.data = pagina;
        result.message = message;
        result.statusCode = status;
        return result;
    }
}