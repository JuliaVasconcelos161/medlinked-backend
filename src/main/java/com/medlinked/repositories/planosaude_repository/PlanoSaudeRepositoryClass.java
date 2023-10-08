package com.medlinked.repositories.planosaude_repository;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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

    @Override
    public PlanoSaude getOnePlanoSaude(Integer idPlanoSaude) {
        try {
            return entityManager.find(PlanoSaude.class,idPlanoSaude);
        } catch (NoResultException e) {
            throw new NoObjectFoundException("Plano de Saúde");
        }
    }

    @Override
    public void delete(PlanoSaude planoSaude) {
        entityManager.remove(planoSaude);
    }
}
