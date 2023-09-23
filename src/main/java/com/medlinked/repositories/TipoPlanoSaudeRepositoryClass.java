package com.medlinked.repositories;

import com.medlinked.entities.TipoPlanoSaude;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoPlanoSaudeRepositoryClass implements TipoPlanoSaudeRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<TipoPlanoSaude> getAllTiposPlanoSaude() {
        StringBuilder consulta = new StringBuilder(" select tipoPlanoSaude from TipoPlanoSaude tipoPlanoSaude ");
        var query = entityManager.createQuery(consulta.toString(), TipoPlanoSaude.class);
        return query.getResultList();
    }
}
