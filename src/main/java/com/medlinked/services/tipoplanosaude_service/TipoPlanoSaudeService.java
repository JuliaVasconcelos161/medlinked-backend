package com.medlinked.services.tipoplanosaude_service;

import com.medlinked.entities.TipoPlanoSaude;

import java.util.List;

public interface TipoPlanoSaudeService {
    List<TipoPlanoSaude> getAllTiposPlanoSaude();

    TipoPlanoSaude getOneTipoPlanoSaude(Integer idTipoPlanoSaude);
}
