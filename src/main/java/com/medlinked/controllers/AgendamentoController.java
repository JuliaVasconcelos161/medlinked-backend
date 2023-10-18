package com.medlinked.controllers;

import com.medlinked.entities.dtos.AgendamentoDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.agedamento_service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
