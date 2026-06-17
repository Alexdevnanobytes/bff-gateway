package com.donaton.bff_gateway.controller;

import com.donaton.bff_gateway.facade.DonacionFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DonacionGatewayControllerTest {

    @Test
    void debeListarDonacionesDesdeGateway() {
        DonacionFacade facade = mock(DonacionFacade.class);
        DonacionGatewayController controller = new DonacionGatewayController(facade);

        Object respuesta = new Object();

        when(facade.listarDonaciones()).thenReturn(respuesta);

        Object resultado = controller.listarDonaciones();

        assertSame(respuesta, resultado);
    }

    @Test
    void debeListarDonacionesPorUsuarioDesdeGateway() {
        DonacionFacade facade = mock(DonacionFacade.class);
        DonacionGatewayController controller = new DonacionGatewayController(facade);

        Object respuesta = new Object();

        when(facade.listarDonacionesPorUsuario(3L)).thenReturn(respuesta);

        Object resultado = controller.listarDonacionesPorUsuario(3L);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeObtenerDonacionDesdeGateway() {
        DonacionFacade facade = mock(DonacionFacade.class);
        DonacionGatewayController controller = new DonacionGatewayController(facade);

        Object respuesta = new Object();

        when(facade.obtenerDonacion(9L)).thenReturn(respuesta);

        Object resultado = controller.obtenerDonacion(9L);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeCrearDonacionDesdeGateway() {
        DonacionFacade facade = mock(DonacionFacade.class);
        DonacionGatewayController controller = new DonacionGatewayController(facade);

        Object dto = new Object();
        Object respuesta = new Object();

        when(facade.crearDonacion(dto)).thenReturn(respuesta);

        Object resultado = controller.crearDonacion(dto);

        assertSame(respuesta, resultado);
    }

    @Test
    void debeListarCentrosDesdeGateway() {
        DonacionFacade facade = mock(DonacionFacade.class);
        DonacionGatewayController controller = new DonacionGatewayController(facade);

        Object respuesta = new Object();

        when(facade.listarCentros()).thenReturn(respuesta);

        Object resultado = controller.listarCentros();

        assertSame(respuesta, resultado);
    }
}
