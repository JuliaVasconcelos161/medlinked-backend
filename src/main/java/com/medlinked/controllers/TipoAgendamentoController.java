package com.medlinked.controllers;

import com.medlinked.services.tipoagendamento_service.TipoAgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipo-agendamento")
public class TipoAgendamentoController {

    private final TipoAgendamentoService tipoAgendamentoService;

    public TipoAgendamentoController(TipoAgendamentoService tipoAgendamentoService) {
        this.tipoAgendamentoService = tipoAgendamentoService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllTiposAgendamento(){
        return ResponseEntity.status(HttpStatus.OK).body(tipoAgendamentoService.getAllTipoAgendamento());
    }
}
