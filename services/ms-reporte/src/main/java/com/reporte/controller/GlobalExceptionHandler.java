package com.reporte.controller;

import com.reporte.common.OperationResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OperationResult<Void>> handleException(Exception ex) {
        OperationResult<Void> errorResult = OperationResult.<Void>builder()
                .isSuccess(false)
                .data(null)
                .message("Error procesando el reporte: " + ex.getMessage())
                .errorCode("INTERNAL_ERROR")
                .statusCode(500)
                .timestamp(LocalDate.now())
                .build();

        return new ResponseEntity<>(errorResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
