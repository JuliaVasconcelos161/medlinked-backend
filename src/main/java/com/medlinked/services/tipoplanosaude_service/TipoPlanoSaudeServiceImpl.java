package com.medlinked.services.tipoplanosaude_service;

import com.medlinked.entities.TipoPlanoSaude;
import com.medlinked.repositories.tipoplanosaude_repository.TipoPlanoSaudeRepository;
import com.medlinked.services.tipoplanosaude_service.TipoPlanoSaudeService;
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
