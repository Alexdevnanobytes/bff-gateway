package com.donaton.bff_gateway.controller;

import com.donaton.bff_gateway.facade.DonacionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway/usuarios")
@RequiredArgsConstructor
public class UsuarioGatewayController {

    private final DonacionFacade donacionFacade;

    @PostMapping("/login")
    public Object login(@RequestBody Object dto) {
        return donacionFacade.login(dto);
    }

    @PostMapping("/registro")
    public Object registrar(@RequestBody Object dto) {
        return donacionFacade.registrar(dto);
    }

    @GetMapping("/{id}")
    public Object obtenerUsuario(@PathVariable Long id) {
        return donacionFacade.obtenerUsuario(id);
    }

    @PutMapping("/{id}")
    public Object actualizarUsuario(@PathVariable Long id, @RequestBody Object dto) {
        return donacionFacade.actualizarUsuario(id, dto);
    }
}