package com.donaton.bff_gateway.client;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DonacionClientTest {

    @Test
    void debeCrearDonacion() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        DonacionClient client = new DonacionClient(restTemplate);

        ReflectionTestUtils.setField(client, "donacionesUrl", "http://localhost:8082");

        Object dto = new Object();
        Object respuesta = new Object();

        when(restTemplate.postForObject(
                "http://localhost:8082/api/donaciones",
                dto,
                Object.class
        )).thenReturn(respuesta);

        Object resultado = client.crearDonacion(dto);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeListarDonaciones() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        DonacionClient client = new DonacionClient(restTemplate);

        ReflectionTestUtils.setField(client, "donacionesUrl", "http://localhost:8082");

        Object respuesta = new Object();

        when(restTemplate.getForObject(
                "http://localhost:8082/api/donaciones",
                Object.class
        )).thenReturn(respuesta);

        Object resultado = client.listar();

        assertSame(respuesta, resultado);
    }

    @Test
    void debeObtenerDonacionPorId() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        DonacionClient client = new DonacionClient(restTemplate);

        ReflectionTestUtils.setField(client, "donacionesUrl", "http://localhost:8082");

        Object respuesta = new Object();

        when(restTemplate.getForObject(
                "http://localhost:8082/api/donaciones/1",
                Object.class
        )).thenReturn(respuesta);

        Object resultado = client.obtener(1L);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeListarDonacionesPorUsuario() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        DonacionClient client = new DonacionClient(restTemplate);

        ReflectionTestUtils.setField(client, "donacionesUrl", "http://localhost:8082");

        Object respuesta = new Object();

        when(restTemplate.getForObject(
                "http://localhost:8082/api/donaciones/usuario/12",
                Object.class
        )).thenReturn(respuesta);

        Object resultado = client.listarPorUsuario(12L);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeListarCentros() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        DonacionClient client = new DonacionClient(restTemplate);

        ReflectionTestUtils.setField(client, "donacionesUrl", "http://localhost:8082");

        Object respuesta = new Object();

        when(restTemplate.getForObject(
                "http://localhost:8082/api/centros",
                Object.class
        )).thenReturn(respuesta);

        Object resultado = client.listarCentros();

        assertSame(respuesta, resultado);
    }
}
