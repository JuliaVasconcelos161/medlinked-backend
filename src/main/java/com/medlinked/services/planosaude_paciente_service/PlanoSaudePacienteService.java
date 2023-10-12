package com.medlinked.services.planosaude_paciente_service;

import com.medlinked.entities.PlanoSaudePaciente;
import com.medlinked.entities.dtos.PlanoSaudePacienteDto;

public interface PlanoSaudePacienteService {
     PlanoSaudePaciente associatePacientePlanoSaude(
             Integer idPaciente, Integer idPlanoSaude, PlanoSaudePacienteDto planoSaudePacienteDto);

    void disassociatePacientePlanoSaude(Integer idPaciente, Integer idPlanoSaude);
}
