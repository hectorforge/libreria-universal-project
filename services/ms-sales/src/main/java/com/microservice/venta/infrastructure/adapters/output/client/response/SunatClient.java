package com.microservice.venta.infrastructure.adapters.output.client.response;

import com.microservice.venta.infrastructure.adapters.input.rest.model.response.DniResponse;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.SunatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Value;


@Service
@RequiredArgsConstructor
public class SunatClient {

    private final WebClient webClient;

    @Value("${sunat.api.token}")
    private String token;

    private static final String BASE_URL = "https://dniruc.apisperu.com/api/v1";

    public Mono<SunatResponse> obtenerUsuarioSunat(String numero) {
        return webClient.get()
                .uri(BASE_URL + "/ruc/{numero}", numero)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(SunatResponse.class);
    }

    public Mono<DniResponse> ObtenerUsuarioReniec(String numero) {
        return webClient.get()
                .uri(BASE_URL + "/dni/{numero}", numero)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(DniResponse.class);
    }

}

