package com.medlinked.controllers;

import com.medlinked.entities.Especialidade;
import com.medlinked.services.EspecialidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @GetMapping
    public ResponseEntity<List<Especialidade>> getAllEspecialidades() {
        return ResponseEntity.status(HttpStatus.OK).body(especialidadeService.getAllEspecialidades());
    }
}
