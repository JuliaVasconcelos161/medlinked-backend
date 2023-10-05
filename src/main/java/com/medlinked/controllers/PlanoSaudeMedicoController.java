package com.medlinked.controllers;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.MedicoPlanoSaudeDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.PlanoSaudeMedicoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plano-saude-medico")
public class PlanoSaudeMedicoController {

    private final PlanoSaudeMedicoService planoSaudeMedicoService;

    public PlanoSaudeMedicoController(PlanoSaudeMedicoService planoSaudeMedicoService) {
        this.planoSaudeMedicoService = planoSaudeMedicoService;
    }


    @Operation(summary = "Atualiza o médico vinculando-o aos planos de saúde recebidos, e retorna os planos vinculados.")
    @PutMapping("/adiciona-planos-medico/{idMedico}")
    public ResponseEntity<Object> updateMedicoPlanosSaude(@PathVariable Integer idMedico,
                                                          @RequestBody @Valid MedicoPlanoSaudeDto medicoPlanoSaudeDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    planoSaudeMedicoService.updateMedicoPlanosSaude(idMedico, medicoPlanoSaudeDto));
        } catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }

    }

    @Operation(summary = "Atualiza o médico removendo plano de saúde utilizando o idPlanoSaude informado.")
    @PutMapping("/update-medico-remove-plano/{idMedico}/{idPlanoSaude}")
    public ResponseEntity<List<PlanoSaude>> updateMedicoRemovePlanoSaude(
            @PathVariable Integer idMedico,
            @PathVariable Integer idPlanoSaude) {
        return ResponseEntity.status(HttpStatus.OK).body(planoSaudeMedicoService.updateMedicoRemovePlanoSaude(idMedico, idPlanoSaude));
    }
}
