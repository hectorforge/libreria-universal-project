package com.microservice.venta.shared;

import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorCatalog {

    private static final Map<ResultCode, String> CATALOG = Map.of(
            ResultCode.VENTA_NOT_FOUND, ResultCode.VENTA_NOT_FOUND.getMessage(),
            ResultCode.VENTA_INVALID, ResultCode.VENTA_INVALID.getMessage(),
            ResultCode.FACTURACION_INVALID, ResultCode.FACTURACION_INVALID.getMessage(),
            ResultCode.FACTURACION_NOT_FOUND, ResultCode.FACTURACION_NOT_FOUND.getMessage(),
            ResultCode.PAGO_NOT_FOUND, ResultCode.PAGO_NOT_FOUND.getMessage(),
            ResultCode.PAGO_INVALID, ResultCode.PAGO_INVALID.getMessage(),
            ResultCode.GENERIC_ERROR, ResultCode.GENERIC_ERROR.getMessage()
    );

    public static String catalog(ResultCode code) {
        return CATALOG.get(code);
    }

}
