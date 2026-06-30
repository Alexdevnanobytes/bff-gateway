package com.donaton.bff_gateway.client;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NecesidadClientTest {

    @Test
    void debeCrearNecesidad() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        NecesidadClient client = new NecesidadClient(restTemplate);

        ReflectionTestUtils.setField(client, "necesidadesUrl", "http://localhost:8083");

        Object dto = new Object();
        Object respuesta = new Object();

        when(restTemplate.postForObject(
                "http://localhost:8083/api/necesidades",
                dto,
                Object.class
        )).thenReturn(respuesta);

        Object resultado = client.crearNecesidad(dto);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeListarNecesidades() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        NecesidadClient client = new NecesidadClient(restTemplate);

        ReflectionTestUtils.setField(client, "necesidadesUrl", "http://localhost:8083");

        Object respuesta = new Object();

        when(restTemplate.getForObject(
                "http://localhost:8083/api/necesidades",
                Object.class
        )).thenReturn(respuesta);

        Object resultado = client.listar();

        assertSame(respuesta, resultado);
    }

    @Test
    void debeObtenerNecesidadPorId() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        NecesidadClient client = new NecesidadClient(restTemplate);

        ReflectionTestUtils.setField(client, "necesidadesUrl", "http://localhost:8083");

        Object respuesta = new Object();

        when(restTemplate.getForObject(
                "http://localhost:8083/api/necesidades/1",
                Object.class
        )).thenReturn(respuesta);

        Object resultado = client.obtener(1L);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeListarPorEstado() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        NecesidadClient client = new NecesidadClient(restTemplate);

        ReflectionTestUtils.setField(client, "necesidadesUrl", "http://localhost:8083");

        Object respuesta = new Object();

        when(restTemplate.getForObject(
                "http://localhost:8083/api/necesidades/estado/PENDIENTE",
                Object.class
        )).thenReturn(respuesta);

        Object resultado = client.listarPorEstado("PENDIENTE");

        assertSame(respuesta, resultado);
    }

    @Test
    void debeListarPorRegion() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        NecesidadClient client = new NecesidadClient(restTemplate);

        ReflectionTestUtils.setField(client, "necesidadesUrl", "http://localhost:8083");

        Object respuesta = new Object();

        when(restTemplate.getForObject(
                "http://localhost:8083/api/necesidades/region/Biobio",
                Object.class
        )).thenReturn(respuesta);

        Object resultado = client.listarPorRegion("Biobio");

        assertSame(respuesta, resultado);
    }
}
