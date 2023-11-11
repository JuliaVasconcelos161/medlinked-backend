package com.medlinked.controllers;

import com.medlinked.entities.dtos.MedicoPlanoSaudeDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.planosaude_medico_service.PlanoSaudeMedicoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plano-saude/medico")
public class PlanoSaudeMedicoController {

    private final PlanoSaudeMedicoService planoSaudeMedicoService;

    public PlanoSaudeMedicoController(PlanoSaudeMedicoService planoSaudeMedicoService) {
        this.planoSaudeMedicoService = planoSaudeMedicoService;
    }


    @Operation(summary = "Associa planos de saúde a médico informado.")
    @PutMapping("/associate/{idMedico}")
    public ResponseEntity<Object> associatePlanosSaudeMedico(@PathVariable Integer idMedico,
                                                             @RequestBody @Valid MedicoPlanoSaudeDto medicoPlanoSaudeDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    planoSaudeMedicoService.associatePlanosSaudeMedico(idMedico, medicoPlanoSaudeDto));
        } catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }

    }

    @Operation(summary = "Disassocia plano de saúde de médico informado.")
    @PutMapping("/disassociate/{idMedico}/{idPlanoSaude}")
    public ResponseEntity<Object> disassociatePlanoSaudeMedico(@PathVariable Integer idMedico,
            @PathVariable Integer idPlanoSaude) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(planoSaudeMedicoService.disassociatePlanoSaudeMedico(idMedico, idPlanoSaude));
        } catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Retorna todos os planos de saúde pelos quais o médico atende.")
    @GetMapping("/{idMedico}")
    public ResponseEntity<Object> getAllPlanosSaudeMedico(
            @PathVariable Integer idMedico,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(planoSaudeMedicoService.getAllPlanosSaudeMedico(idMedico, page, pageSize));
    }
}
