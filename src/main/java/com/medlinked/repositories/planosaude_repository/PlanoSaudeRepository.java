package com.medlinked.repositories.planosaude_repository;

import com.medlinked.entities.PlanoSaude;

import java.util.List;

public interface PlanoSaudeRepository {
    List<PlanoSaude> getAllPlanosSaude(Integer page, Integer pageSize);

    PlanoSaude save(PlanoSaude planoSaude);

    boolean existsPlanoSaudeByDescricao(String descricao);

    PlanoSaude getOnePlanoSaude(Integer idPlanoSaude);

    void delete(PlanoSaude planoSaude);

    Long countPlanosSaude();
}
