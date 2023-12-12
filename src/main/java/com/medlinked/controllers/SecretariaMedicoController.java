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
            secretariaMedicoService.associateSecretariaMedico(idSecretaria, idMedico);
            return ResponseEntity.status(HttpStatus.OK).body("Médico associado com sucesso.");
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Desassocia a secretária do médico informado.")
    @PutMapping("/disassociate/{idSecretaria}/{idMedico}")
    public ResponseEntity<Object> disassociateSecretariaMedico(@PathVariable Integer idSecretaria,
                                                            @PathVariable Integer idMedico) {
        try{
            secretariaMedicoService.disassociateSecretariaMedico(idSecretaria, idMedico);
            return ResponseEntity.status(HttpStatus.OK).body("Médico disassociado com sucesso.");
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Retorna todos os médicos de uma secretária de forma paginada.")
    @GetMapping("/paginado/{idSecretaria}")
    public ResponseEntity<Object> getAllMedicosSecretariaPaginado(
            @PathVariable Integer idSecretaria,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(secretariaMedicoService.getAllMedicosSecretariaPaginado(idSecretaria, page, pageSize));
    }

    @Operation(summary = "Retorna todos os médicos de uma secretária.")
    @GetMapping("/{idSecretaria}")
    public ResponseEntity<Object> getAllMedicosSecretaria(
            @PathVariable Integer idSecretaria) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(secretariaMedicoService.getAllMedicosSecretaria(idSecretaria));
    }
}
