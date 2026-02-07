package com.reporte.client;

import com.reporte.common.OperationResult;
import com.reporte.dto.client.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cliente", fallback = ClientClientFallback.class)
public interface ClientClient {
    @GetMapping("/api/v1/clients/{id}")
    OperationResult<ClientDto> getClientById(@PathVariable("id") String id);
}
