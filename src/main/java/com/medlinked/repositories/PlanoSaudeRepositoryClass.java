package com.medlinked.repositories;

import com.medlinked.entities.PlanoSaude;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanoSaudeRepositoryClass implements PlanoSaudeRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PlanoSaude> getAllPlanosSaude() {
        StringBuilder consulta = new StringBuilder(" select planoSaude from PlanoSaude planoSaude ");
        var query = entityManager.createQuery(consulta.toString(), PlanoSaude.class);
        return query.getResultList();
    }
}
