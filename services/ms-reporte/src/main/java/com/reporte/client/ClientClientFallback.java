package com.reporte.client;

import com.reporte.common.OperationResult;
import com.reporte.dto.client.ClientDto;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class ClientClientFallback implements ClientClient {
    @Override
    public OperationResult<ClientDto> getClientById(String id) {
        // Retorna siempre el cliente VIP #1
        ClientDto client = ClientDto.builder()
                .id(id)
                .businessName("Corporación Lider S.A.C.")
                .email("contacto@lider.pe")
                .phone("+51 999-123-456")
                .build();

        return new OperationResult<>(true, client, "Mock Data", null, null, 200, LocalDate.now());
    }
}
