package com.donaton.bff_gateway.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UsuarioClient {

    private final RestTemplate restTemplate;

    @Value("${ms.usuarios.url}")
    private String usuariosUrl;

    public Object login(Object dto) {
        return restTemplate.postForObject(
                usuariosUrl + "/api/usuarios/login",
                dto,
                Object.class
        );
    }

    public Object registrar(Object dto) {
        return restTemplate.postForObject(
                usuariosUrl + "/api/usuarios/registro",
                dto,
                Object.class
        );
    }

    public Object obtener(Long id) {
        return restTemplate.getForObject(
                usuariosUrl + "/api/usuarios/" + id,
                Object.class
        );
    }

    public Object actualizar(Long id, Object dto) {
        restTemplate.put(
                usuariosUrl + "/api/usuarios/" + id,
                dto
        );

        return Map.of(
                "mensaje", "Usuario actualizado correctamente",
                "codigo", 200
        );
    }
}