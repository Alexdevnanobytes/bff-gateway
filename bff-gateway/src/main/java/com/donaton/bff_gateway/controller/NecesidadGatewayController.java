package com.donaton.bff_gateway.controller;

import com.donaton.bff_gateway.facade.NecesidadFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller que actúa como Gateway para las operaciones de necesidades.
 * Conecta el Frontend con el microservicio de necesidades a través de la Facade.
 */
@RestController
@RequestMapping("/gateway/necesidades")
@RequiredArgsConstructor
public class NecesidadGatewayController {

    private final NecesidadFacade necesidadFacade;

    // 1. Reportar una nueva necesidad (POST)
    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody Object dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(necesidadFacade.crearNecesidad(dto));
    }

    // 2. Listar TODAS las necesidades (GET)
    @GetMapping
    public ResponseEntity<Object> listar() {
        return ResponseEntity.ok(necesidadFacade.listarNecesidades());
    }

    // 3. Obtener el detalle de una necesidad por su ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Object> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(necesidadFacade.obtenerNecesidad(id));
    }

    // 4. Filtrar necesidades por estado (GET)
    @GetMapping("/estado/{estado}")
    public ResponseEntity<Object> porEstado(@PathVariable String estado) {
        return ResponseEntity.ok(necesidadFacade.listarPorEstado(estado));
    }

    // 5. Filtrar necesidades por región (GET)
    @GetMapping("/region/{region}")
    public ResponseEntity<Object> porRegion(@PathVariable String region) {
        return ResponseEntity.ok(necesidadFacade.listarPorRegion(region));
    }
}
