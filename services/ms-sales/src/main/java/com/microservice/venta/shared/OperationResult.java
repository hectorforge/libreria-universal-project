package com.microservice.venta.shared;

import com.microservice.venta.shared.pagination.PaginaResult;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class OperationResult<T> {

    private boolean success;
    private T data;
    private String message;
    private Map<String, String> errors;
    private String errorCode;
    private int statusCode;
    private LocalDate timestamp;

    private OperationResult() {
        this.timestamp = LocalDate.now();
        this.errors = new HashMap<>();
    }

    public static <T> OperationResult<T> success(T data, String message, int status) {
        OperationResult<T> result = new OperationResult<>();
        result.success = true;
        result.data = data;
        result.message = message;
        result.statusCode = status;
        return result;
    }

    public static <T> OperationResult<PaginaResult<T>> successPagination(
            PaginaResult<T> pagina,
            String message,
            int status
    ) {
        OperationResult<PaginaResult<T>> result = new OperationResult<>();
        result.success = true;
        result.data = pagina;
        result.message = message;
        result.statusCode = status;
        return result;
    }

    public static <T> OperationResult<T> successWithoutData(String message, int status) {
        OperationResult<T> result = new OperationResult<>();
        result.success = true;
        result.message = message;
        result.statusCode = status;
        return result;
    }

    public static <T> OperationResult<T> validationError(String error) {
        OperationResult<T> result = new OperationResult<>();
        result.success = false;
        result.message = "Error de validación";
        result.statusCode = 400;
        result.errors.put("error", error);
        return result;
    }

    public static <T> OperationResult<T> failure(String message, int status, String errorCode) {
        OperationResult<T> result = new OperationResult<>();
        result.success = false;
        result.message = message;
        result.statusCode = status;
        result.errorCode = errorCode;
        return result;
    }

}
