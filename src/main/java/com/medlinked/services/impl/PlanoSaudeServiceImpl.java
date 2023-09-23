package com.medlinked.services.impl;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.repositories.PlanoSaudeRepository;
import com.medlinked.services.PlanoSaudeService;
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
}
