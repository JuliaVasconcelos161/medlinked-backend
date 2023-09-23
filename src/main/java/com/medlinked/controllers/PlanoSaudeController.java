package com.medlinked.controllers;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.services.PlanoSaudeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plano-saude")
public class PlanoSaudeController {

    private final PlanoSaudeService planoSaudeService;

    public PlanoSaudeController(PlanoSaudeService planoSaudeService) {
        this.planoSaudeService = planoSaudeService;
    }

    @GetMapping
    public ResponseEntity<List<PlanoSaude>> getAllPlanosSaude() {
        return ResponseEntity.status(HttpStatus.OK).body(planoSaudeService.getAllPlanosSaude());
    }
}
