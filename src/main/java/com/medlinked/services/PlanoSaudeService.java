package com.medlinked.services;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.PlanoSaudeDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface PlanoSaudeService {
    List<PlanoSaude> getAllPlanosSaude();

    PlanoSaude createPlanoSaude(PlanoSaudeDto planoSaudeDto);

    void deletePlanoSaude(Integer idPlanoSaude);
}
