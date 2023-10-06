package com.medlinked.services;

import com.medlinked.entities.Paciente;
import com.medlinked.entities.dtos.PacienteDto;

public interface PacienteService {
    Paciente createPaciente(PacienteDto pacienteDto);
}
