package com.donaton.bff_gateway.facade;

import com.donaton.bff_gateway.client.DonacionClient;
import com.donaton.bff_gateway.client.UsuarioClient;
import com.donaton.bff_gateway.dto.ErrorResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DonacionFacade {

    private final UsuarioClient usuarioClient;
    private final DonacionClient donacionClient;

    @CircuitBreaker(name = "ms-usuarios", fallbackMethod = "fallbackUsuarios")
    public Object login(Object dto) {
        return usuarioClient.login(dto);
    }

    @CircuitBreaker(name = "ms-usuarios", fallbackMethod = "fallbackUsuarios")
    public Object registrar(Object dto) {
        return usuarioClient.registrar(dto);
    }

    @CircuitBreaker(name = "ms-usuarios", fallbackMethod = "fallbackUsuarios")
    public Object obtenerUsuario(Long id) {
        return usuarioClient.obtener(id);
    }

    @CircuitBreaker(name = "ms-usuarios", fallbackMethod = "fallbackUsuarios")
    public Object actualizarUsuario(Long id, Object dto) {
        return usuarioClient.actualizar(id, dto);
    }

    @CircuitBreaker(name = "ms-donaciones", fallbackMethod = "fallbackDonaciones")
    public Object crearDonacion(Object dto) {
        return donacionClient.crearDonacion(dto);
    }

    @CircuitBreaker(name = "ms-donaciones", fallbackMethod = "fallbackDonaciones")
    public Object listarDonaciones() {
        return donacionClient.listar();
    }

    @CircuitBreaker(name = "ms-donaciones", fallbackMethod = "fallbackDonaciones")
    public Object obtenerDonacion(Long id) {
        return donacionClient.obtener(id);
    }

    @CircuitBreaker(name = "ms-donaciones", fallbackMethod = "fallbackDonaciones")
    public Object listarCentros() {
        return donacionClient.listarCentros();
    }

    public Object fallbackUsuarios(Exception e) {
        return new ErrorResponseDTO(true, "Servicio usuarios no disponible", 503);
    }

    public Object fallbackDonaciones(Exception e) {
        return new ErrorResponseDTO(true, "Servicio donaciones no disponible", 503);
    }
}
