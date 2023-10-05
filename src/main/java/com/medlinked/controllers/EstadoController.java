package com.medlinked.controllers;

import com.medlinked.entities.Estado;
import com.medlinked.services.EstadoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @Operation(summary = "Retorna todos os estados cadastrados no sistema.")
    @GetMapping
    public ResponseEntity<List<Estado>> getAllEstados() {
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.getAllEstados());
    }
}
