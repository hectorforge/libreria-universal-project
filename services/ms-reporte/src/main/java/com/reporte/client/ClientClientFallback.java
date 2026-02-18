package com.reporte.client;

import com.reporte.common.OperationResult;
import com.reporte.dto.client.ClientDto;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class ClientClientFallback implements ClientClient {
    @Override
    public OperationResult<ClientDto> getClientById(String id) {
        // DATOS FICTICIOS VARIADOS SEGÚN EL ID
        String name = "Cliente Genérico";
        String email = "cliente@email.com";

        if (id.endsWith("111")) { name = "Corporación Lider S.A.C."; email = "contacto@lider.pe"; }
        else if (id.endsWith("222")) { name = "Inversiones Globales EIRL"; email = "ventas@globales.com.pe"; }
        else if (id.endsWith("333")) { name = "Constructora Andes Group"; email = "proyectos@andes.pe"; }
        else if (id.endsWith("444")) { name = "Ferretería Central S.A."; email = "contacto@central.pe"; }
        else if (id.endsWith("555")) { name = "Importadora del Norte"; email = "import@norte.pe"; }

        ClientDto client = ClientDto.builder()
                .id(id)
                .businessName(name)
                .email(email)
                .phone("+51 999-123-456")
                .build();

        return new OperationResult<>(true, client, "Mock Data", null, null, 200, LocalDate.now());
    }
}
