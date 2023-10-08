package com.medlinked.controllers;

import com.medlinked.entities.dtos.SecretariaDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.secretaria_service.SecretariaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secretaria")
@CrossOrigin(origins = "*", maxAge = 4600, allowedHeaders = "*")
public class SecretariaController {

    private final SecretariaService secretariaService;

    public SecretariaController(SecretariaService secretariaService) {
        this.secretariaService = secretariaService;
    }

    @Operation(summary = "Cria secretária e retorna jwt de autenticacao.")
    @PostMapping("/create")
    public ResponseEntity<Object> createSecretaria(@RequestBody @Valid SecretariaDto secretariaDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(secretariaService.createSecretaria(secretariaDto));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
