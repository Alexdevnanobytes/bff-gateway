package com.donaton.bff_gateway.facade;

import com.donaton.bff_gateway.client.DonacionClient;
import com.donaton.bff_gateway.client.UsuarioClient;
import com.donaton.bff_gateway.dto.ErrorResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DonacionFacadeTest {

    @Test
    void debeDelegarLoginAUsuarioClient() {
        UsuarioClient usuarioClient = mock(UsuarioClient.class);
        DonacionClient donacionClient = mock(DonacionClient.class);
        DonacionFacade facade = new DonacionFacade(usuarioClient, donacionClient);

        Object dto = new Object();
        Object respuesta = new Object();

        when(usuarioClient.login(dto)).thenReturn(respuesta);

        Object resultado = facade.login(dto);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeDelegarListarDonacionesADonacionClient() {
        UsuarioClient usuarioClient = mock(UsuarioClient.class);
        DonacionClient donacionClient = mock(DonacionClient.class);
        DonacionFacade facade = new DonacionFacade(usuarioClient, donacionClient);

        Object respuesta = new Object();

        when(donacionClient.listar()).thenReturn(respuesta);

        Object resultado = facade.listarDonaciones();

        assertSame(respuesta, resultado);
    }

    @Test
    void debeRetornarFallbackUsuarios() {
        UsuarioClient usuarioClient = mock(UsuarioClient.class);
        DonacionClient donacionClient = mock(DonacionClient.class);
        DonacionFacade facade = new DonacionFacade(usuarioClient, donacionClient);

        Object resultado = facade.fallbackUsuarios(new RuntimeException("error"));

        assertInstanceOf(ErrorResponseDTO.class, resultado);
    }

    @Test
    void debeRetornarFallbackDonaciones() {
        UsuarioClient usuarioClient = mock(UsuarioClient.class);
        DonacionClient donacionClient = mock(DonacionClient.class);
        DonacionFacade facade = new DonacionFacade(usuarioClient, donacionClient);

        Object resultado = facade.fallbackDonaciones(new RuntimeException("error"));

        assertInstanceOf(ErrorResponseDTO.class, resultado);
    }
}
