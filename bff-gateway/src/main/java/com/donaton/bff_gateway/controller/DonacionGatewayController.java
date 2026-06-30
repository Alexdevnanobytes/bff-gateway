package com.donaton.bff_gateway.controller;

import com.donaton.bff_gateway.facade.DonacionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Controller que actúa como Gateway para las operaciones de donaciones y centros.
 * Conecta el Frontend con el microservicio de donaciones a través de la Facade.
 */
@RestController
@RequestMapping("/gateway")
@RequiredArgsConstructor
public class DonacionGatewayController {

    private final DonacionFacade donacionFacade;

    // 1. Crear una nueva donación (POST)
    @PostMapping("/donaciones")
    public Object crearDonacion(@RequestBody Object dto) {
        return donacionFacade.crearDonacion(dto);
    }

    // 2. Listar TODAS las donaciones (GET)
    @GetMapping("/donaciones")
    public Object listarDonaciones() {
        return donacionFacade.listarDonaciones();
    }

    // 3. NUEVO: Listar donaciones de un USUARIO específico (GET)
    // Este es el endpoint que usa tu página "Mis Donaciones"
    @GetMapping("/donaciones/usuario/{id}")
    public Object listarDonacionesPorUsuario(@PathVariable Long id) {
        return donacionFacade.listarDonacionesPorUsuario(id);
    }

    // 4. Obtener el detalle de una sola donación por su ID (GET)
    @GetMapping("/donaciones/{id}")
    public Object obtenerDonacion(@PathVariable Long id) {
        return donacionFacade.obtenerDonacion(id);
    }

    // 5. Listar todos los centros de acopio (GET)
    @GetMapping("/centros")
    public Object listarCentros() {
        return donacionFacade.listarCentros();
    }
}