package com.donaton.bff_gateway.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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

    public Object listarPorUsuario(Long id) {
        return restTemplate.getForObject(
                donacionesUrl + "/api/donaciones/usuario/" + id,
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

    // 🚀 MODIFICADO: Ahora usa exchange() enviando las cabeceras JSON obligatorias para MySQL
    public Object actualizarEstado(Long id, Object dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(dto, headers);

        return restTemplate.exchange(
                donacionesUrl + "/api/donaciones/" + id + "/estado",
                HttpMethod.PUT,
                entity,
                Object.class
        ).getBody();
    }
}