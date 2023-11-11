package com.medlinked.controllers;

import com.medlinked.entities.dtos.PlanoSaudePacienteDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.planosaude_paciente_service.PlanoSaudePacienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plano-saude/paciente")
public class PlanoSaudePacienteController {

    private final PlanoSaudePacienteService planoSaudePacienteService;

    public PlanoSaudePacienteController(PlanoSaudePacienteService planoSaudePacienteService) {
        this.planoSaudePacienteService = planoSaudePacienteService;
    }

    @Operation(summary = "Associa paciente ao plano de saúde informado.")
    @PutMapping("/associate/{idPaciente}/{idPlanoSaude}")
    public ResponseEntity<Object> associatePacientePlanoSaude(@PathVariable Integer idPaciente,
                                                              @PathVariable Integer idPlanoSaude,
                                                              @RequestBody PlanoSaudePacienteDto planoSaudePacienteDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(planoSaudePacienteService
                            .associatePacientePlanoSaude(idPaciente, idPlanoSaude, planoSaudePacienteDto));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Desvincula paciente de plano de saúde informado.")
    @PutMapping("/disassociate/{idPaciente}/{idPlanoSaude}")
    public ResponseEntity<Object> disassociatePacientePlanoSaude(@PathVariable Integer idPaciente,
                                                                 @PathVariable Integer idPlanoSaude) {
        try {
            planoSaudePacienteService.disassociatePacientePlanoSaude(idPaciente, idPlanoSaude);
            return ResponseEntity.status(HttpStatus.OK).body("Plano saúde desvinculado de Paciente com sucesso!");
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Retorna todos os planos de saude de um paciente.")
    @GetMapping("/{idPaciente}")
    public ResponseEntity<Object> getAllPlanosSaudePaciente(@PathVariable Integer idPaciente) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(planoSaudePacienteService.getAllPlanosSaudePaciente(idPaciente));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Retorna todos os planos de saude de um paciente que também são planos de um determinado médico.")
    @GetMapping("/medico/{idPaciente}/{idMedico}")
    public ResponseEntity<Object> getAllPlanosSaudePacienteMedico(@PathVariable Integer idPaciente,
                                                                  @PathVariable Integer idMedico) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(planoSaudePacienteService.getAllPlanosSaudePacienteMedico(idPaciente, idMedico));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
