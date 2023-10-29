package com.medlinked.controllers;

import com.medlinked.entities.dtos.AgendamentoDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.agendamento_service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @Operation(summary = "Cria novo agendamento e o retorna.")
    @PostMapping("/create")
    public ResponseEntity<Object> createAgendamento(@RequestBody @Valid AgendamentoDto agendamentoDto) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.createAgendamento(agendamentoDto));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Altera agendamento e retorna agendamento alterado.")
    @PutMapping("/update/{idAgendamento}")
    public ResponseEntity<Object> updateAgendamento(@RequestBody @Valid AgendamentoDto agendamentoDto,
                                                    @PathVariable Integer idAgendamento) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(agendamentoService.updateAgendamento(agendamentoDto, idAgendamento));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Retorna todos os agendamentos dos médicos de uma secretária.")
    @GetMapping("/{idSecretaria}")
    public ResponseEntity<Object> getAllAgendamentosMedicosSecretaria(@PathVariable Integer idSecretaria,
                                                                      @RequestParam(required = false) Integer idMedico,
                                                                      @RequestParam(required = false) Integer idPaciente,
                                                                      @RequestParam(required = false) Integer mes,
                                                                      @RequestParam(required = false) Integer ano,
                                                                      @RequestParam(required = false) Integer dia) {
        return ResponseEntity.status(HttpStatus.OK).body(agendamentoService
                .getAllAgendamentosMedicosSecretaria(idSecretaria, idMedico, idPaciente, mes, ano, dia));
    }

    @Operation(summary = "Deleta agendamento utilizando idAgendamento informado.")
    @DeleteMapping("/delete/{idAgendamento}")
    public ResponseEntity<Object> deleteAgendamento(@PathVariable Integer idAgendamento) {
        try{
            agendamentoService.deleteAgendamento(idAgendamento);
            return ResponseEntity.status(HttpStatus.OK).body("Agendamento deletado com sucesso.");
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
