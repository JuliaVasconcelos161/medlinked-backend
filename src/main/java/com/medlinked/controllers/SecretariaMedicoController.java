package com.medlinked.controllers;

import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.secretaria_medico_service.SecretariaMedicoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secretaria/medico")
public class SecretariaMedicoController {

    private final SecretariaMedicoService secretariaMedicoService;

    public SecretariaMedicoController(SecretariaMedicoService secretariaMedicoService) {
        this.secretariaMedicoService = secretariaMedicoService;
    }

    @Operation(summary = "Associa a secretária ao médico informado.")
    @PutMapping("/associate/{idSecretaria}/{idMedico}")
    public ResponseEntity<Object> associateSecretariaMedico(@PathVariable Integer idSecretaria,
                                                            @PathVariable Integer idMedico) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(secretariaMedicoService
                    .associateSecretariaMedico(idSecretaria, idMedico));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
