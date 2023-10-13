package com.medlinked.controllers;

import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.secretaria_medico_service.SecretariaMedicoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Desassocia a secretária do médico informado.")
    @PutMapping("/disassociate/{idSecretaria}/{idMedico}")
    public ResponseEntity<Object> disassociateSecretariaMedico(@PathVariable Integer idSecretaria,
                                                            @PathVariable Integer idMedico) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(secretariaMedicoService
                    .disassociateSecretariaMedico(idSecretaria, idMedico));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Retorna todos os médicos de uma secretária.")
    @GetMapping("/{idSecretaria}")
    public ResponseEntity<Object> getAllMedicosSecretaria(
            @PathVariable Integer idSecretaria,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(secretariaMedicoService.getAllMedicosSecretaria(idSecretaria, page, pageSize));
    }
}
