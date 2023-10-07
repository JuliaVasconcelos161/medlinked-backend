package com.medlinked.services.impl;

import com.medlinked.entities.TipoPlanoSaude;
import com.medlinked.repositories.TipoPlanoSaudeRepository;
import com.medlinked.services.TipoPlanoSaudeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPlanoSaudeServiceImpl implements TipoPlanoSaudeService {

    private final TipoPlanoSaudeRepository tipoPlanoSaudeRepository;

    public TipoPlanoSaudeServiceImpl(TipoPlanoSaudeRepository tipoPlanoSaudeRepository) {
        this.tipoPlanoSaudeRepository = tipoPlanoSaudeRepository;
    }

    @Override
    public List<TipoPlanoSaude> getAllTiposPlanoSaude() {
        return tipoPlanoSaudeRepository.getAllTiposPlanoSaude();
    }

    @Override
    public TipoPlanoSaude getOneTipoPlanoSaude(Integer idTipoPlanoSaude) {
        return tipoPlanoSaudeRepository.getOneTipoPlanoSaude(idTipoPlanoSaude);
    }
}
