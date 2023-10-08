package com.medlinked.services.planosaude_medico_service;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.MedicoPlanoSaudeDto;

import java.util.List;

public interface PlanoSaudeMedicoService {
    List<PlanoSaude> updateMedicoPlanosSaude(Integer idMedico, MedicoPlanoSaudeDto medicoPlanoSaudeDto);

    List<PlanoSaude> updateMedicoRemovePlanoSaude(Integer idMedico, Integer idPlanoSaude);
}