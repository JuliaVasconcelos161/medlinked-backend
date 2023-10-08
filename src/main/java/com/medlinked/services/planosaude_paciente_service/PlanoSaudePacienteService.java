package com.medlinked.services.planosaude_paciente_service;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.PlanoSaudePaciente;
import com.medlinked.entities.dtos.PlanoSaudePacienteDto;

import java.util.List;

public interface PlanoSaudePacienteService {
     PlanoSaudePaciente associatePacientePlanoSaude(
             Integer idPaciente, Integer idPlanoSaude, PlanoSaudePacienteDto planoSaudePacienteDto);
}