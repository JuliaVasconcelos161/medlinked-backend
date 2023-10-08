package com.medlinked.controllers;

import com.medlinked.entities.Estado;
import com.medlinked.services.estado_service.EstadoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estado")
@CrossOrigin(origins = "*", maxAge = 4600, allowedHeaders = "*")
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
