package com.medlinked.services.paciente_service;

import com.medlinked.entities.Paciente;
import com.medlinked.entities.dtos.PacienteDto;
import com.medlinked.entities.dtos.PacienteResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PacienteService {
    PacienteResponseDto createPaciente(PacienteDto pacienteDto);

    PacienteResponseDto getOnePaciente(Integer idPaciente);

    PacienteResponseDto updatePaciente(Integer idPaciente, PacienteDto pacienteDto);


    Page<Paciente> getAllPacientes(String nomePaciente, String cpf, int page, int pageSize);
}
