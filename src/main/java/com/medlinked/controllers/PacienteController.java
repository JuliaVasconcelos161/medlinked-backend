package com.medlinked.controllers;

import com.medlinked.entities.dtos.PacienteDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Object> createPaciente(@RequestBody @Valid PacienteDto pacienteDto) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pacienteService.createPaciente(pacienteDto));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
