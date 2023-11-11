package com.medlinked.controllers;

import com.medlinked.entities.dtos.UpdateSenhaUsuarioDto;
import com.medlinked.entities.dtos.UsuarioRegisterDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.usuario_service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Autentica usuário e retorna jwt.")
    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticateUsuario(@RequestBody @Valid UsuarioRegisterDto usuarioRegisterDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.authenticate(usuarioRegisterDto));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Altera a senha de usuário e o retorna.")
    @PutMapping("/update-senha/{idUsuario}")
    public ResponseEntity<Object> updateSenhaUsuario(@PathVariable Integer idUsuario,
                                                     @RequestBody @Valid UpdateSenhaUsuarioDto updateSenhaUsuarioDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.updateSenhaUsuario(updateSenhaUsuarioDto, idUsuario));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
