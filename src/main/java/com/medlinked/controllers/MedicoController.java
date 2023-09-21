package com.medlinked.controllers;

import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.exceptions.MedLinkedException;
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

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
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
    public ResponseEntity<Object> getOneMedico(@PathVariable Long idMedico) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(medicoService.getOneMedico(idMedico));
        }catch (MedLinkedException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

//    @DeleteMapping("/{idMedico}")
//    public ResponseEntity<Object> deleteMedico(@PathVariable Long idMedico) {
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(medicoService.deleteMedico(idMedico));
//        }catch (MedLinkedException e) {
//            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
//        }
//    }
}
