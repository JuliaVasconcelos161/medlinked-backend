package com.medlinked.controllers;

import com.medlinked.entities.dtos.PlanoSaudePacienteDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.planosaude_paciente_service.PlanoSaudePacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plano-saude/paciente")
@CrossOrigin(origins = "*", maxAge = 4600, allowedHeaders = "*")
public class PlanoSaudePacienteController {

    private final PlanoSaudePacienteService planoSaudePacienteService;

    public PlanoSaudePacienteController(PlanoSaudePacienteService planoSaudePacienteService) {
        this.planoSaudePacienteService = planoSaudePacienteService;
    }

    @PutMapping("/{idPaciente}/{idPlanoSaude}")
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
}
