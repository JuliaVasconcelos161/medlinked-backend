package com.medlinked.repositories;

import com.medlinked.entities.PlanoSaude;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
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

    @Override
    public PlanoSaude save(PlanoSaude planoSaude) {
        entityManager.persist(planoSaude);
        return planoSaude;
    }

    @Override
    public boolean existsPlanoSaudeByDescricao(String descricao) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from PlanoSaude planoSaude ");
        consulta.append(" where planoSaude.descricao = :DESCRICAO ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        query.setParameter("DESCRICAO", descricao);
        return query.getSingleResult() > 0;
    }
}
