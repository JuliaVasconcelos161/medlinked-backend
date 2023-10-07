package com.medlinked.services;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.PlanoSaudePacienteDto;

import java.util.List;

public interface PlanoSaudePacienteService {
     List<PlanoSaude> associatePacientePlanoSaude(
             Integer idPaciente, Integer idPlanoSaude, PlanoSaudePacienteDto planoSaudePacienteDto);
}
