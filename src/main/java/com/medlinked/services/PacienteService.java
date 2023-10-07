package com.medlinked.services;

import com.medlinked.entities.dtos.PacienteDto;
import com.medlinked.entities.dtos.PacienteResponseDto;

public interface PacienteService {
    PacienteResponseDto createPaciente(PacienteDto pacienteDto);

    PacienteResponseDto getOnePaciente(Integer idPaciente);

    PacienteResponseDto updatePaciente(Integer idPaciente, PacienteDto pacienteDto);
}
