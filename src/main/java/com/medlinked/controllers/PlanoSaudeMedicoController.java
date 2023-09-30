package com.medlinked.controllers;

import com.medlinked.entities.dtos.MedicoPlanoSaudeDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.PlanoSaudeMedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plano-saude-medico")
public class PlanoSaudeMedicoController {

    private final PlanoSaudeMedicoService planoSaudeMedicoService;

    public PlanoSaudeMedicoController(PlanoSaudeMedicoService planoSaudeMedicoService) {
        this.planoSaudeMedicoService = planoSaudeMedicoService;
    }


    @PutMapping("/{idMedico}")
    public ResponseEntity<Object> updateMedicoPlanosSaude(@PathVariable Integer idMedico,
                                                          @RequestBody MedicoPlanoSaudeDto medicoPlanoSaudeDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    planoSaudeMedicoService.updateMedicoPlanosSaude(idMedico, medicoPlanoSaudeDto));
        } catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }

    }
}
