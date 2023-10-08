package com.medlinked.controllers;

import com.medlinked.entities.Especialidade;
import com.medlinked.services.especialidade_service.EspecialidadeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/especialidade")
@CrossOrigin(origins = "*", maxAge = 4600, allowedHeaders = "*")
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @Operation(summary = "Retorna todas as especialidades cadastradas no sistema.")
    @GetMapping
    public ResponseEntity<List<Especialidade>> getAllEspecialidades() {
        return ResponseEntity.status(HttpStatus.OK).body(especialidadeService.getAllEspecialidades());
    }
}
