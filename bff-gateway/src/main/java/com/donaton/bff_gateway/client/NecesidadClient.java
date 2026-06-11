package com.donaton.bff_gateway.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class NecesidadClient {

    private final RestTemplate restTemplate;

    @Value("${ms.necesidades.url}")
    private String necesidadesUrl;

    public Object crearNecesidad(Object dto) {
        return restTemplate.postForObject(
                necesidadesUrl + "/api/necesidades",
                dto,
                Object.class
        );
    }

    public Object listar() {
        return restTemplate.getForObject(
                necesidadesUrl + "/api/necesidades",
                Object.class
        );
    }

    public Object obtener(Long id) {
        return restTemplate.getForObject(
                necesidadesUrl + "/api/necesidades/" + id,
                Object.class
        );
    }

    public Object listarPorEstado(String estado) {
        return restTemplate.getForObject(
                necesidadesUrl + "/api/necesidades/estado/" + estado,
                Object.class
        );
    }

    public Object listarPorRegion(String region) {
        return restTemplate.getForObject(
                necesidadesUrl + "/api/necesidades/region/" + region,
                Object.class
        );
    }
}
