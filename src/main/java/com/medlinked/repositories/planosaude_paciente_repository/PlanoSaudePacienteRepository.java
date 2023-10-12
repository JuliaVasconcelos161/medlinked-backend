package com.medlinked.repositories.planosaude_paciente_repository;

import com.medlinked.entities.PlanoSaudePaciente;

public interface PlanoSaudePacienteRepository {
    PlanoSaudePaciente savePlanoSaudePaciente(PlanoSaudePaciente planoSaudePaciente);

    void disassociatePacientePlanoSaude(Integer idPaciente, Integer idPlanoSaude);
}
