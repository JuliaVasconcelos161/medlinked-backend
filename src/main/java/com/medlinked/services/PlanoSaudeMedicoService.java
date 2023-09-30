package com.medlinked.services;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.MedicoPlanoSaudeDto;

import java.util.List;

public interface PlanoSaudeMedicoService {
    List<PlanoSaude> updateMedicoPlanosSaude(Integer idMedico, MedicoPlanoSaudeDto medicoPlanoSaudeDto);
}
