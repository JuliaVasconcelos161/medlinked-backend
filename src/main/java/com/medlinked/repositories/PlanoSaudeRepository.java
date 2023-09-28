package com.medlinked.repositories;

import com.medlinked.entities.PlanoSaude;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlanoSaudeRepository {
    List<PlanoSaude> getAllPlanosSaude();

    PlanoSaude save(PlanoSaude planoSaude);

    boolean existsPlanoSaudeByDescricao(String descricao);

    PlanoSaude getOnePlanoSaude(Integer idPlanoSaude);

    void delete(PlanoSaude planoSaude);
}
