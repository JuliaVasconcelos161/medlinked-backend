package com.medlinked.controllers;

import com.medlinked.entities.dtos.PacienteDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Operation(summary = "Cria um novo paciente com seu endereço e o retorna.")
    @PostMapping
    public ResponseEntity<Object> createPaciente(@RequestBody @Valid PacienteDto pacienteDto) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pacienteService.createPaciente(pacienteDto));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Retorna um paciente e seu endereço utilizando idPaciente.")
    @GetMapping("/{idPaciente}")
    public ResponseEntity<Object> getOnePaciente(@PathVariable Integer idPaciente) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pacienteService.getOnePaciente(idPaciente));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualiza os dados de um paciente utilizando o idPaciente.")
    @PutMapping("/{idPaciente}")
    public ResponseEntity<Object> updatePaciente(@PathVariable Integer idPaciente,
                                                 @RequestBody @Valid PacienteDto pacienteDto) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pacienteService.updatePaciente(idPaciente, pacienteDto));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}