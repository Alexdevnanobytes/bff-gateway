package com.donaton.bff_gateway.facade;

import com.donaton.bff_gateway.client.DonacionClient;
import com.donaton.bff_gateway.client.UsuarioClient;
import com.donaton.bff_gateway.dto.ErrorResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DonacionFacadeTest {

    private final UsuarioClient usuarioClient = mock(UsuarioClient.class);
    private final DonacionClient donacionClient = mock(DonacionClient.class);
    private final DonacionFacade facade = new DonacionFacade(usuarioClient, donacionClient);

    @Test
    void debeDelegarLoginAUsuarioClient() {
        Object dto = new Object();
        Object respuesta = new Object();

        when(usuarioClient.login(dto)).thenReturn(respuesta);

        Object resultado = facade.login(dto);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeDelegarRegistrarAUsuarioClient() {
        Object dto = new Object();
        Object respuesta = new Object();

        when(usuarioClient.registrar(dto)).thenReturn(respuesta);

        Object resultado = facade.registrar(dto);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeDelegarObtenerUsuarioAUsuarioClient() {
        Object respuesta = new Object();

        when(usuarioClient.obtener(5L)).thenReturn(respuesta);

        Object resultado = facade.obtenerUsuario(5L);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeDelegarActualizarUsuarioAUsuarioClient() {
        Object dto = new Object();
        Object respuesta = new Object();

        when(usuarioClient.actualizar(5L, dto)).thenReturn(respuesta);

        Object resultado = facade.actualizarUsuario(5L, dto);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeDelegarCrearDonacionADonacionClient() {
        Object dto = new Object();
        Object respuesta = new Object();

        when(donacionClient.crearDonacion(dto)).thenReturn(respuesta);

        Object resultado = facade.crearDonacion(dto);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeDelegarListarDonacionesADonacionClient() {
        Object respuesta = new Object();

        when(donacionClient.listar()).thenReturn(respuesta);

        Object resultado = facade.listarDonaciones();

        assertSame(respuesta, resultado);
    }

    @Test
    void debeDelegarListarDonacionesPorUsuarioADonacionClient() {
        Object respuesta = new Object();

        when(donacionClient.listarPorUsuario(8L)).thenReturn(respuesta);

        Object resultado = facade.listarDonacionesPorUsuario(8L);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeDelegarObtenerDonacionADonacionClient() {
        Object respuesta = new Object();

        when(donacionClient.obtener(8L)).thenReturn(respuesta);

        Object resultado = facade.obtenerDonacion(8L);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeDelegarListarCentrosADonacionClient() {
        Object respuesta = new Object();

        when(donacionClient.listarCentros()).thenReturn(respuesta);

        Object resultado = facade.listarCentros();

        assertSame(respuesta, resultado);
    }

    @Test
    void debeRetornarFallbackUsuarios() {
        Object resultado = facade.fallbackUsuarios(new RuntimeException("error"));

        assertInstanceOf(ErrorResponseDTO.class, resultado);
        ErrorResponseDTO error = (ErrorResponseDTO) resultado;
        assertTrue(error.isError());
        assertEquals("Servicio usuarios no disponible temporalmente", error.getMensaje());
        assertEquals(503, error.getCodigo());
    }

    @Test
    void debeRetornarFallbackDonaciones() {
        Object resultado = facade.fallbackDonaciones(new RuntimeException("error"));

        assertInstanceOf(ErrorResponseDTO.class, resultado);
        ErrorResponseDTO error = (ErrorResponseDTO) resultado;
        assertTrue(error.isError());
        assertEquals("Servicio de donaciones no disponible temporalmente", error.getMensaje());
        assertEquals(503, error.getCodigo());
    }
}
