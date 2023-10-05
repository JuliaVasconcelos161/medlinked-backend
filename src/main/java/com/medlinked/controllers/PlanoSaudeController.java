package com.medlinked.controllers;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.PlanoSaudeDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.PlanoSaudeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plano-saude")
public class PlanoSaudeController {

    private final PlanoSaudeService planoSaudeService;

    public PlanoSaudeController(PlanoSaudeService planoSaudeService) {
        this.planoSaudeService = planoSaudeService;
    }

    @PostMapping
    public ResponseEntity<Object> createPlanoSaude(@RequestBody @Valid PlanoSaudeDto planoSaudeDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(planoSaudeService.createPlanoSaude(planoSaudeDto));
        } catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<PlanoSaude>> getAllPlanosSaude() {
        return ResponseEntity.status(HttpStatus.OK).body(planoSaudeService.getAllPlanosSaude());
    }

    @DeleteMapping("/{idPlanoSaude}")
    public ResponseEntity<Object> deletePlanoSaude(@PathVariable Integer idPlanoSaude) {
        try {
            planoSaudeService.deletePlanoSaude(idPlanoSaude);
            return ResponseEntity.status(HttpStatus.OK).body("Plano de saúde deletado com sucesso.");
        } catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
