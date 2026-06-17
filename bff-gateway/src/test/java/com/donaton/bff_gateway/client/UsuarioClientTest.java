package com.donaton.bff_gateway.client;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioClientTest {

    @Test
    void debeLlamarLogin() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        UsuarioClient client = new UsuarioClient(restTemplate);

        ReflectionTestUtils.setField(client, "usuariosUrl", "http://localhost:8081");

        Object dto = new Object();
        Object respuesta = new Object();

        when(restTemplate.postForObject(
                "http://localhost:8081/api/usuarios/login",
                dto,
                Object.class
        )).thenReturn(respuesta);

        Object resultado = client.login(dto);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeRegistrarUsuario() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        UsuarioClient client = new UsuarioClient(restTemplate);

        ReflectionTestUtils.setField(client, "usuariosUrl", "http://localhost:8081");

        Object dto = new Object();
        Object respuesta = new Object();

        when(restTemplate.postForObject(
                "http://localhost:8081/api/usuarios/registro",
                dto,
                Object.class
        )).thenReturn(respuesta);

        Object resultado = client.registrar(dto);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeObtenerUsuarioPorId() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        UsuarioClient client = new UsuarioClient(restTemplate);

        ReflectionTestUtils.setField(client, "usuariosUrl", "http://localhost:8081");

        Object respuesta = new Object();

        when(restTemplate.getForObject(
                "http://localhost:8081/api/usuarios/1",
                Object.class
        )).thenReturn(respuesta);

        Object resultado = client.obtener(1L);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeActualizarUsuario() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        UsuarioClient client = new UsuarioClient(restTemplate);

        ReflectionTestUtils.setField(client, "usuariosUrl", "http://localhost:8081");

        Object dto = new Object();

        Object resultado = client.actualizar(1L, dto);

        verify(restTemplate, times(1)).put(
                "http://localhost:8081/api/usuarios/1",
                dto
        );

        assertNotNull(resultado);
    }
}
