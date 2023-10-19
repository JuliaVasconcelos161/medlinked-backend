package com.medlinked.controllers;

import com.medlinked.entities.dtos.PessoaCpfDto;
import com.medlinked.entities.dtos.UsuarioRegisterDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.pessoa_service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
@CrossOrigin(origins = "*", maxAge = 4600, allowedHeaders = "*")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Operation(summary = "Retorna pessoa através do cpf.")
    @PostMapping("/cpf")
    public ResponseEntity<Object> getPessoaByCpf(@RequestBody @Valid PessoaCpfDto pessoaCpfDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPessoaByCpf(pessoaCpfDto.getCpf()));
    }
}
