package com.medlinked.repositories.planosaude_paciente_repository;

import com.medlinked.entities.PlanoSaudePaciente;
import com.medlinked.entities.dtos.PlanoSaudePacienteResponseDto;

import java.util.List;

public interface PlanoSaudePacienteRepository {
    PlanoSaudePaciente savePlanoSaudePaciente(PlanoSaudePaciente planoSaudePaciente);

    void disassociatePacientePlanoSaude(Integer idPaciente, Integer idPlanoSaude);

    void disassociateAllPacientesPlanoSaude(Integer idPlanoSaude);

    List<PlanoSaudePacienteResponseDto> buildPlanoSaudePacienteResponseDto(Integer idPaciente);

    void disassociateAllPlanosSaudePaciente(Integer idPaciente);
}
