package com.donaton.bff_gateway.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class DonacionClient {

    private final RestTemplate restTemplate;

    @Value("${ms.donaciones.url}")
    private String donacionesUrl;

    public Object crearDonacion(Object dto) {
        return restTemplate.postForObject(
                donacionesUrl + "/api/donaciones",
                dto,
                Object.class
        );
    }

    public Object listar() {
        return restTemplate.getForObject(
                donacionesUrl + "/api/donaciones",
                Object.class
        );
    }

    public Object obtener(Long id) {
        return restTemplate.getForObject(
                donacionesUrl + "/api/donaciones/" + id,
                Object.class
        );
    }

    public Object listarCentros() {
        return restTemplate.getForObject(
                donacionesUrl + "/api/centros",
                Object.class
        );
    }
}