package com.medlinked.controllers;

import com.medlinked.entities.TipoPlanoSaude;
import com.medlinked.services.tipoplanosaude_service.TipoPlanoSaudeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipo-plano-saude")
@CrossOrigin(origins = "*", maxAge = 4600, allowedHeaders = "*")
public class TipoPlanoSaudeController {

    private final TipoPlanoSaudeService tipoPlanoSaudeService;

    public TipoPlanoSaudeController(TipoPlanoSaudeService tipoPlanoSaudeService) {
        this.tipoPlanoSaudeService = tipoPlanoSaudeService;
    }

    @Operation(summary = "Retorna todos os tipos de plano de saúde cadastrados no sistema.")
    @GetMapping
    public ResponseEntity<List<TipoPlanoSaude>> getAllTiposPlanoSaude() {
        return ResponseEntity.status(HttpStatus.OK).body(tipoPlanoSaudeService.getAllTiposPlanoSaude());
    }

}
