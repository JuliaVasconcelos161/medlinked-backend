package com.medlinked.services.planosaude_medico_service;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.MedicoPlanoSaudeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PlanoSaudeMedicoService {
    List<PlanoSaude> associatePlanosSaudeMedico(Integer idMedico, MedicoPlanoSaudeDto medicoPlanoSaudeDto);

    List<PlanoSaude> disassociatePlanoSaudeMedico(Integer idMedico, Integer idPlanoSaude);

    Page<PlanoSaude> getAllPlanosSaudeMedico(Integer idMedico, Integer page, Integer pageSize);
}
