package com.medlinked.services.medico_service;

import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoCrmResponseDto;
import com.medlinked.entities.dtos.MedicoDto;

import java.util.List;

public interface MedicoService {
    MedicoCrmResponseDto createMedico(MedicoDto medicoDto, Integer idSecretaria);

    List<Medico> getAll();

    MedicoCrmResponseDto updateMedico(Integer idMedico, MedicoDto medicoDto);

    void deleteMedico(Integer idMedico);
}
