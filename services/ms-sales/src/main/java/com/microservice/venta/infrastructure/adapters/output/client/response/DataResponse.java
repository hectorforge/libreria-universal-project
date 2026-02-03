package com.microservice.venta.infrastructure.adapters.output.client.response;

import lombok.Data;

@Data
public class DataResponse<T> {

    private boolean success;
    private T data;
    private String message;

}
