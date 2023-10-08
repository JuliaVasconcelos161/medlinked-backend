package com.medlinked.repositories.tipoplanosaude_repository;

import com.medlinked.entities.TipoPlanoSaude;

import java.util.List;

public interface TipoPlanoSaudeRepository {
    List<TipoPlanoSaude> getAllTiposPlanoSaude();

    TipoPlanoSaude getOneTipoPlanoSaude(Integer idTipoPlanoSaude);
}
