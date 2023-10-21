package com.medlinked.controllers;

import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.medico_service.MedicoService;
import com.medlinked.services.medicocrm_service.MedicoCrmService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
@CrossOrigin(origins = "*", maxAge = 4600, allowedHeaders = "*")
public class MedicoController {

    private final MedicoService medicoService;

    private final MedicoCrmService medicoCrmService;

    public MedicoController(MedicoService medicoService, MedicoCrmService medicoCrmService) {
        this.medicoService = medicoService;
        this.medicoCrmService = medicoCrmService;
    }

    @Operation(summary = "Cria um novo médico e o retorna, o associando com a secretária que o cadastrou.")
    @PostMapping("/create/{idSecretaria}")
    public ResponseEntity<Object> createMedico(@RequestBody @Valid MedicoDto medicoDto,
                                               @PathVariable Integer idSecretaria) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(medicoService.createMedico(medicoDto, idSecretaria));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Retorna todos os médicos cadastrados no sistema.")
    @GetMapping
    public ResponseEntity<List<Medico>> getAllMedicos(){
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.getAll());
    }

    @Operation(summary = "Retorna médico utilizando idMedico.")
    @GetMapping("/{idMedico}")
    public ResponseEntity<Object> getOneMedico(@PathVariable Integer idMedico) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(medicoCrmService.buildMedicoCrmResponseDto(idMedico));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @Operation(summary = "Altera dados de um médico utilizando o idMedico informado, e retorna os dados alterados.")
    @PutMapping("/update/{idMedico}")
    public ResponseEntity<Object> updateMedico(@PathVariable Integer idMedico, @RequestBody @Valid MedicoDto medicoDto) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(medicoService.updateMedico(idMedico, medicoDto));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }
}
