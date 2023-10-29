package com.medlinked.controllers;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.PlanoSaudeDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.planosaude_service.PlanoSaudeService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Cria um novo plano de saúde e o retorna.")
    @PostMapping("/create")
    public ResponseEntity<Object> createPlanoSaude(@RequestBody @Valid PlanoSaudeDto planoSaudeDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(planoSaudeService.createPlanoSaude(planoSaudeDto));
        } catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Retorna todos os planos de saúde cadastrados no sistema.")
    @GetMapping
    public ResponseEntity<List<PlanoSaude>> getAllPlanosSaude() {
        return ResponseEntity.status(HttpStatus.OK).body(planoSaudeService.getAllPlanosSaude());
    }

    @Operation(summary = "Retorna todos os planos de saúde cadastrados no sistema de forma paginada.")
    @GetMapping("/paginado")
    public ResponseEntity<Object> getAllPlanosSaudePaginado(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(planoSaudeService.getAllPlanosSaudePaginado(page, pageSize));
    }

    @Operation(summary = "Deleta o plano de saúde utilizando idPlanoSaude informado.")
    @DeleteMapping("/delete/{idPlanoSaude}")
    public ResponseEntity<Object> deletePlanoSaude(@PathVariable Integer idPlanoSaude) {
        try {
            planoSaudeService.deletePlanoSaude(idPlanoSaude);
            return ResponseEntity.status(HttpStatus.OK).body("Plano de saúde deletado com sucesso.");
        } catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
