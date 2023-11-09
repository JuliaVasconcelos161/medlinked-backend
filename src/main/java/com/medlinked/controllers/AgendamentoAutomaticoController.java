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
@RequestMapping("/agendamento-automatico")
public class AgendamentoAutomaticoController {
    private final AgendamentoAutomaticoService agendamentoAutomaticoService;

    public AgendamentoAutomaticoController(AgendamentoAutomaticoService agendamentoAutomaticoService) {
        this.agendamentoAutomaticoService = agendamentoAutomaticoService;
    }

    @Operation(summary = "Deleta agendamento utilizando idAgendamento informado.")
    @PostMapping
    public ResponseEntity<Object> createAgendamentoAutomatico(@RequestBody @Valid AgendamentoAutomaticoDto agendamentoAutomaticoDto) {
        try{
            agendamentoAutomaticoService.createAgendamentosAutomaticos(agendamentoAutomaticoDto);
            return ResponseEntity.status(HttpStatus.OK).body("Agendamentos realizados");
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
