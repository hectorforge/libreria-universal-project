package com.reporte.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private String id;
    private String businessName; // razonSocial
    private String email;
    private String phone;
    private String address;
}
