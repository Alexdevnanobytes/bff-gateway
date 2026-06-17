package com.donaton.bff_gateway.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseDTOTest {

    @Test
    void debeCrearRespuestaConConstructorCompleto() {
        ErrorResponseDTO dto = new ErrorResponseDTO(true, "Servicio no disponible", 503);

        assertTrue(dto.isError());
        assertEquals("Servicio no disponible", dto.getMensaje());
        assertEquals(503, dto.getCodigo());
    }

    @Test
    void debePermitirModificarValores() {
        ErrorResponseDTO dto = new ErrorResponseDTO();

        dto.setError(true);
        dto.setMensaje("Token invalido");
        dto.setCodigo(401);

        assertTrue(dto.isError());
        assertEquals("Token invalido", dto.getMensaje());
        assertEquals(401, dto.getCodigo());
    }
}
