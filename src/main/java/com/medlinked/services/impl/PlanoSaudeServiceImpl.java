package com.medlinked.services.impl;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.PlanoSaudeDto;
import com.medlinked.exceptions.ExistsDescricao;
import com.medlinked.repositories.PlanoSaudeRepository;
import com.medlinked.services.PlanoSaudeService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoSaudeServiceImpl implements PlanoSaudeService {

    private final PlanoSaudeRepository planoSaudeRepository;

    public PlanoSaudeServiceImpl(PlanoSaudeRepository planoSaudeRepository) {
        this.planoSaudeRepository = planoSaudeRepository;
    }

    @Override
    public List<PlanoSaude> getAllPlanosSaude() {
        return planoSaudeRepository.getAllPlanosSaude();
    }

    @Override
    @Transactional
    public PlanoSaude createPlanoSaude(PlanoSaudeDto planoSaudeDto) {
        if(this.existsPlanoSaudeByDescricao(planoSaudeDto.getDescricao()))
            throw new ExistsDescricao("Plano de Saúde");
        PlanoSaude planoSaude = PlanoSaude
                .builder()
                .descricao(planoSaudeDto.getDescricao())
                .build();
        return planoSaudeRepository.save(planoSaude);
    }

    private boolean existsPlanoSaudeByDescricao(String descricao) {
        return planoSaudeRepository.existsPlanoSaudeByDescricao(descricao);
    }
}
