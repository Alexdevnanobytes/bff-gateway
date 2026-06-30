package com.donaton.bff_gateway.controller;

import com.donaton.bff_gateway.facade.NecesidadFacade;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NecesidadGatewayControllerTest {

    @Test
    void debeCrearNecesidadDesdeGateway() {
        NecesidadFacade facade = mock(NecesidadFacade.class);
        NecesidadGatewayController controller = new NecesidadGatewayController(facade);

        Object dto = new Object();
        Object respuesta = new Object();

        when(facade.crearNecesidad(dto)).thenReturn(respuesta);

        ResponseEntity<Object> resultado = controller.crear(dto);

        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertSame(respuesta, resultado.getBody());
    }

    @Test
    void debeListarNecesidadesDesdeGateway() {
        NecesidadFacade facade = mock(NecesidadFacade.class);
        NecesidadGatewayController controller = new NecesidadGatewayController(facade);

        Object respuesta = new Object();

        when(facade.listarNecesidades()).thenReturn(respuesta);

        ResponseEntity<Object> resultado = controller.listar();

        assertSame(respuesta, resultado.getBody());
    }

    @Test
    void debeObtenerNecesidadDesdeGateway() {
        NecesidadFacade facade = mock(NecesidadFacade.class);
        NecesidadGatewayController controller = new NecesidadGatewayController(facade);

        Object respuesta = new Object();

        when(facade.obtenerNecesidad(1L)).thenReturn(respuesta);

        ResponseEntity<Object> resultado = controller.obtener(1L);

        assertSame(respuesta, resultado.getBody());
    }

    @Test
    void debeFiltrarPorEstadoDesdeGateway() {
        NecesidadFacade facade = mock(NecesidadFacade.class);
        NecesidadGatewayController controller = new NecesidadGatewayController(facade);

        Object respuesta = new Object();

        when(facade.listarPorEstado("PENDIENTE")).thenReturn(respuesta);

        ResponseEntity<Object> resultado = controller.porEstado("PENDIENTE");

        assertSame(respuesta, resultado.getBody());
    }

    @Test
    void debeFiltrarPorRegionDesdeGateway() {
        NecesidadFacade facade = mock(NecesidadFacade.class);
        NecesidadGatewayController controller = new NecesidadGatewayController(facade);

        Object respuesta = new Object();

        when(facade.listarPorRegion("Biobio")).thenReturn(respuesta);

        ResponseEntity<Object> resultado = controller.porRegion("Biobio");

        assertSame(respuesta, resultado.getBody());
    }
}
