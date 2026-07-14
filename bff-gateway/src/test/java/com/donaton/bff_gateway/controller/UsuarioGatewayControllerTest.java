package com.donaton.bff_gateway.controller;

import com.donaton.bff_gateway.facade.DonacionFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioGatewayControllerTest {

    @Test
    void debeEjecutarLoginDesdeGateway() {
        DonacionFacade facade = mock(DonacionFacade.class);
        UsuarioGatewayController controller = new UsuarioGatewayController(facade);

        Object dto = new Object();
        Object respuesta = new Object();

        when(facade.login(dto)).thenReturn(respuesta);

        Object resultado = controller.login(dto);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeRegistrarUsuarioDesdeGateway() {
        DonacionFacade facade = mock(DonacionFacade.class);
        UsuarioGatewayController controller = new UsuarioGatewayController(facade);

        Object dto = new Object();
        Object respuesta = new Object();

        when(facade.registrar(dto)).thenReturn(respuesta);

        Object resultado = controller.registrar(dto);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeObtenerUsuarioDesdeGateway() {
        DonacionFacade facade = mock(DonacionFacade.class);
        UsuarioGatewayController controller = new UsuarioGatewayController(facade);

        Object respuesta = new Object();

        when(facade.obtenerUsuario(1L)).thenReturn(respuesta);

        Object resultado = controller.obtenerUsuario(1L);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeActualizarUsuarioDesdeGateway() {
        DonacionFacade facade = mock(DonacionFacade.class);
        UsuarioGatewayController controller = new UsuarioGatewayController(facade);

        Object dto = new Object();
        Object respuesta = new Object();

        when(facade.actualizarUsuario(4L, dto)).thenReturn(respuesta);

        Object resultado = controller.actualizarUsuario(4L, dto);

        assertSame(respuesta, resultado);
    }
}
