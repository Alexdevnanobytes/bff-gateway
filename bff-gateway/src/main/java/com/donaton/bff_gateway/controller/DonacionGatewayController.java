package com.donaton.bff_gateway.controller;

import com.donaton.bff_gateway.facade.DonacionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway")
@RequiredArgsConstructor
public class DonacionGatewayController {

    private final DonacionFacade donacionFacade;

    @PostMapping("/donaciones")
    public Object crearDonacion(@RequestBody Object dto) {
        return donacionFacade.crearDonacion(dto);
    }

    @GetMapping("/donaciones")
    public Object listarDonaciones() {
        return donacionFacade.listarDonaciones();
    }

    @GetMapping("/donaciones/{id}")
    public Object obtenerDonacion(@PathVariable Long id) {
        return donacionFacade.obtenerDonacion(id);
    }

    @GetMapping("/centros")
    public Object listarCentros() {
        return donacionFacade.listarCentros();
    }
}
