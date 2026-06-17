package com.donaton.bff_gateway.facade;

import com.donaton.bff_gateway.client.NecesidadClient;
import com.donaton.bff_gateway.dto.ErrorResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NecesidadFacadeTest {

    private final NecesidadClient client = mock(NecesidadClient.class);
    private final NecesidadFacade facade = new NecesidadFacade(client);

    @Test
    void debeDelegarCrearNecesidad() {
        Object dto = new Object();
        Object respuesta = new Object();

        when(client.crearNecesidad(dto)).thenReturn(respuesta);

        assertSame(respuesta, facade.crearNecesidad(dto));
    }

    @Test
    void debeDelegarListarNecesidades() {
        Object respuesta = new Object();

        when(client.listar()).thenReturn(respuesta);

        assertSame(respuesta, facade.listarNecesidades());
    }

    @Test
    void debeDelegarObtenerNecesidad() {
        Object respuesta = new Object();

        when(client.obtener(7L)).thenReturn(respuesta);

        assertSame(respuesta, facade.obtenerNecesidad(7L));
    }

    @Test
    void debeDelegarListarPorEstado() {
        Object respuesta = new Object();

        when(client.listarPorEstado("PENDIENTE")).thenReturn(respuesta);

        assertSame(respuesta, facade.listarPorEstado("PENDIENTE"));
    }

    @Test
    void debeDelegarListarPorRegion() {
        Object respuesta = new Object();

        when(client.listarPorRegion("Biobio")).thenReturn(respuesta);

        assertSame(respuesta, facade.listarPorRegion("Biobio"));
    }

    @Test
    void debeRetornarFallbackNecesidades() {
        Object resultado = facade.fallbackNecesidades(new RuntimeException("error"));

        assertInstanceOf(ErrorResponseDTO.class, resultado);
        ErrorResponseDTO error = (ErrorResponseDTO) resultado;
        assertTrue(error.isError());
        assertEquals("Servicio necesidades no disponible temporalmente", error.getMensaje());
        assertEquals(503, error.getCodigo());
    }
}
