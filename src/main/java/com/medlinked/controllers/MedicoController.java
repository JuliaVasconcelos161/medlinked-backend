package com.medlinked.controllers;

import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.services.MedicoCrmService;
import com.medlinked.services.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    private final MedicoCrmService medicoCrmService;

    public MedicoController(MedicoService medicoService, MedicoCrmService medicoCrmService) {
        this.medicoService = medicoService;
        this.medicoCrmService = medicoCrmService;
    }

    @PostMapping
    public ResponseEntity<Object> createMedico(@RequestBody @Valid MedicoDto medicoDto) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(medicoService.save(medicoDto));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Medico>> getAllMedicos(){
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.getAll());
    }

    @GetMapping("/{idMedico}")
    public ResponseEntity<Object> getOneMedico(@PathVariable Integer idMedico) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(medicoCrmService.getOneCrmByMedico(idMedico));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

}
