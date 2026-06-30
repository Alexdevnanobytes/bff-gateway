package com.donaton.bff_gateway.facade;

import com.donaton.bff_gateway.client.NecesidadClient;
import com.donaton.bff_gateway.dto.ErrorResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NecesidadFacade {

    private final NecesidadClient necesidadClient;

    @CircuitBreaker(name = "ms-necesidades", fallbackMethod = "fallbackNecesidades")
    public Object crearNecesidad(Object dto) {
        return necesidadClient.crearNecesidad(dto);
    }

    @CircuitBreaker(name = "ms-necesidades", fallbackMethod = "fallbackNecesidades")
    public Object listarNecesidades() {
        return necesidadClient.listar();
    }

    @CircuitBreaker(name = "ms-necesidades", fallbackMethod = "fallbackNecesidades")
    public Object obtenerNecesidad(Long id) {
        return necesidadClient.obtener(id);
    }

    @CircuitBreaker(name = "ms-necesidades", fallbackMethod = "fallbackNecesidades")
    public Object listarPorEstado(String estado) {
        return necesidadClient.listarPorEstado(estado);
    }

    @CircuitBreaker(name = "ms-necesidades", fallbackMethod = "fallbackNecesidades")
    public Object listarPorRegion(String region) {
        return necesidadClient.listarPorRegion(region);
    }

    public Object fallbackNecesidades(Exception e) {
        return new ErrorResponseDTO(true, "Servicio necesidades no disponible temporalmente", 503);
    }
}
