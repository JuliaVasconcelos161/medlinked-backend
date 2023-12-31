package com.medlinked.controllers;

import com.medlinked.entities.dtos.AgendamentoAutomaticoDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.agendamento_service.AgendamentoAutomaticoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamento-automatico/create")
public class AgendamentoAutomaticoController {
    private final AgendamentoAutomaticoService agendamentoAutomaticoService;

    public AgendamentoAutomaticoController(AgendamentoAutomaticoService agendamentoAutomaticoService) {
        this.agendamentoAutomaticoService = agendamentoAutomaticoService;
    }

    @Operation(summary = "Cria agendamentos para um intervalo definido deixando a agenda preparada para apenas depois vincula-lo a um paciente.")
    @PostMapping
    public ResponseEntity<Object> createAgendamentosAutomaticos(@RequestBody @Valid AgendamentoAutomaticoDto agendamentoAutomaticoDto) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(agendamentoAutomaticoService.createAgendamentosAutomaticos(agendamentoAutomaticoDto));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
