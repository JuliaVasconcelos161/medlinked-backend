package com.medlinked.controllers;

import com.medlinked.entities.dtos.PessoaCpfDto;
import com.medlinked.services.pessoa_service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Operation(summary = "Retorna pessoa através do cpf.")
    @GetMapping("/cpf")
    public ResponseEntity<Object> getPessoaByCpf(@RequestBody @Valid PessoaCpfDto pessoaCpfDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPessoaByCpf(pessoaCpfDto.getCpf()));
    }
}
