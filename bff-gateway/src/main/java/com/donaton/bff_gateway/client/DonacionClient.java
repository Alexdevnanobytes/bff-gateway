package com.donaton.bff_gateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

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

    // MÉTODO AGREGADO: Para buscar donaciones de un usuario específico
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

    // 🌟 NUEVO MÉTODO ADAPTADO: Actualiza el estado mediante un PUT que retorna el objeto modificado
    public Object actualizarEstado(Long id, Object estadoDto) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(estadoDto);
        
        return restTemplate.exchange(
                donacionesUrl + "/api/donaciones/" + id + "/estado",
                HttpMethod.PUT,
                requestEntity,
                Object.class
        ).getBody();
    }

    public Object listarCentros() {
        return restTemplate.getForObject(
                donacionesUrl + "/api/centros",
                Object.class
        );
    }
}