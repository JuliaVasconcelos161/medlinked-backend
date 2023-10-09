package com.medlinked.controllers;

import com.medlinked.entities.dtos.UsuarioRegisterDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.usuario_service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticateUsuario(@RequestBody @Valid UsuarioRegisterDto usuarioRegisterDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.authenticate(usuarioRegisterDto));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
