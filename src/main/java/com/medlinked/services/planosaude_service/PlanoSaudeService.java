package com.medlinked.services.planosaude_service;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.PlanoSaudeDto;
import org.springframework.data.domain.Page;

import java.util.List;
public interface PlanoSaudeService {

    List<PlanoSaude> getAllPlanosSaude();

    PlanoSaude createPlanoSaude(PlanoSaudeDto planoSaudeDto);

    void deletePlanoSaude(Integer idPlanoSaude);

    Page<PlanoSaude> getAllPlanosSaudePaginado(Integer page, Integer pageSize);
}
