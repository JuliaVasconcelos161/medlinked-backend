package com.medlinked.repositories.planosaude_paciente_repository;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.PlanoSaudePaciente;

import java.util.List;

public interface PlanoSaudePacienteRepository {
    PlanoSaudePaciente savePlanoSaudePaciente(PlanoSaudePaciente planoSaudePaciente);

    void disassociatePacientePlanoSaude(Integer idPaciente, Integer idPlanoSaude);

    void disassociatePlanoSaudeAllPacientes(Integer idPlanoSaude);

    List<PlanoSaude> getAllPlanosSaudePaciente(Integer idPaciente);
}
