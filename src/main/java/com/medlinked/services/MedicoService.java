package com.medlinked.services;

import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.entities.dtos.MedicoResponseDto;

import java.util.List;

public interface MedicoService {
    MedicoResponseDto save(MedicoDto medicoDto);

    List<Medico> getAll();

    MedicoResponseDto getOneMedico(Integer idMedico);

}
