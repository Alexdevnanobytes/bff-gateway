package com.donaton.bff_gateway.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
<<<<<<< HEAD
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
=======
>>>>>>> b72499bb6489d2c1f2568c89e7f2bd8660ad7b6f
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

<<<<<<< HEAD
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

=======
>>>>>>> b72499bb6489d2c1f2568c89e7f2bd8660ad7b6f
    public Object listarCentros() {
        return restTemplate.getForObject(
                donacionesUrl + "/api/centros",
                Object.class
        );
    }
}