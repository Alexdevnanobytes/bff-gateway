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

    // --- MÉTODOS DE USUARIOS ---

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

    // --- MÉTODOS DE DONACIONES ---

    @CircuitBreaker(name = "ms-donaciones", fallbackMethod = "fallbackDonaciones")
    public Object crearDonacion(Object dto) {
        return donacionClient.crearDonacion(dto);
    }

    @CircuitBreaker(name = "ms-donaciones", fallbackMethod = "fallbackDonaciones")
    public Object listarDonaciones() {
        return donacionClient.listar();
    }

    // NUEVO: Método para obtener donaciones filtradas por ID de usuario
    @CircuitBreaker(name = "ms-donaciones", fallbackMethod = "fallbackDonaciones")
    public Object listarDonacionesPorUsuario(Long id) {
        return donacionClient.listarPorUsuario(id);
    }

    @CircuitBreaker(name = "ms-donaciones", fallbackMethod = "fallbackDonaciones")
    public Object obtenerDonacion(Long id) {
        return donacionClient.obtener(id);
    }

    @CircuitBreaker(name = "ms-donaciones", fallbackMethod = "fallbackDonaciones")
    public Object listarCentros() {
        return donacionClient.listarCentros();
    }

    // --- MÉTODOS FALLBACK (Resiliencia) ---

    public Object fallbackUsuarios(Exception e) {
        return new ErrorResponseDTO(true, "Servicio usuarios no disponible temporalmente", 503);
    }

    public Object fallbackDonaciones(Exception e) {
        return new ErrorResponseDTO(true, "Servicio de donaciones no disponible temporalmente", 503);
    }
}