package com.medlinked.controllers;

import com.medlinked.entities.dtos.SecretariaDto;
import com.medlinked.entities.dtos.SecretariaUsuarioDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.secretaria_service.SecretariaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secretaria")
public class SecretariaController {

    private final SecretariaService secretariaService;

    public SecretariaController(SecretariaService secretariaService) {
        this.secretariaService = secretariaService;
    }

    @Operation(summary = "Cria secretária e retorna jwt de autenticacao.")
    @PostMapping("/create")
    public ResponseEntity<Object> createSecretaria(@RequestBody @Valid SecretariaUsuarioDto secretariaUsuarioDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(secretariaService.createSecretaria(secretariaUsuarioDto));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualiza secretária e retorna secretaria com dados atualizados.")
    @PutMapping("/update/{idSecretaria}")
    public ResponseEntity<Object> updateSecretaria(@PathVariable Integer idSecretaria,
                                                   @RequestBody @Valid SecretariaDto secretariaDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(secretariaService.updateSecretaria(secretariaDto, idSecretaria));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Retorna uma secretária utilizando o idSecretaria informado.")
    @GetMapping("/{idSecretaria}")
    public ResponseEntity<Object> getOneSecretaria(@PathVariable Integer idSecretaria) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(secretariaService.getOneSecretaria(idSecretaria));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
    @Operation(summary = "Deleta secretária e seu usuário.")
    @DeleteMapping("/delete/{idSecretaria}")
    public ResponseEntity<Object> deleteSecretaria(@PathVariable Integer idSecretaria) {
        try {
            secretariaService.deleteSecretaria(idSecretaria);
            return ResponseEntity.status(HttpStatus.OK).body("Secretária deletada com sucesso.");
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
