package com.reporte.client;

import com.reporte.dto.client.ClientDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ClientDummyRepository {

    private final Map<String, ClientDto> clientDatabase = new HashMap<>();

    public ClientDummyRepository() {
        // Mocks con los datos de bd de ventas
        addClient("a1b2c3d4-0001-0001-0001-000000000001", "Ramírez Huanca Jorge", "jorge.ramirez@email.com");
        addClient("a1b2c3d4-0001-0001-0001-000000000002", "Quispe Mamani Lucía", "lucia.quispe@email.com");
        addClient("a1b2c3d4-0001-0001-0001-000000000003", "Flores Ccopa Roberto", "roberto.flores@email.com");
        addClient("a1b2c3d4-0001-0001-0001-000000000004", "Educativa Los Andes S.A.C.", "logistica@colegiolosandes.edu.pe");
        addClient("a1b2c3d4-0001-0001-0001-000000000005", "Contadores Asociados S.R.L.", "compras@contadoresasociados.com");
        addClient("a1b2c3d4-0001-0001-0001-000000000006", "Condori Apaza Miriam", "miriam.condori@email.com");
        addClient("a1b2c3d4-0001-0001-0001-000000000007", "Constructora Horizonte S.A.C.", "admin@constructorahorizonte.com");
        addClient("a1b2c3d4-0001-0001-0001-000000000008", "Ttito Huillca Carmen", "carmen.ttito@email.com");

        // Mock extra por si Pablo usó IDs distintos en sus pruebas de ventas
        addClient("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb", "Cliente de Prueba Ventas", "prueba@ventas.com");
    }

    private void addClient(String id, String name, String email) {
        clientDatabase.put(id, ClientDto.builder()
                .id(id)
                .businessName(name)
                .email(email)
                .build());
    }

    public ClientDto getClientById(String id) {
        // Devuelve el cliente si existe, si no, devuelve uno genérico
        return clientDatabase.getOrDefault(id,
                ClientDto.builder()
                        .id(id)
                        .businessName("Cliente Desconocido (" + id.substring(0, 8) + ")")
                        .email("sin-correo@mail.com")
                        .build()
        );
    }
}
