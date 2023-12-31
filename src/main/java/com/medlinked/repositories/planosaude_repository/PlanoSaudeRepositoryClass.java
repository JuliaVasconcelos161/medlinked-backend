package com.medlinked.repositories.planosaude_repository;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanoSaudeRepositoryClass implements PlanoSaudeRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PlanoSaude> getAllPlanosSaude(Integer page, Integer pageSize) {
        StringBuilder consulta = new StringBuilder(" select planoSaude from PlanoSaude planoSaude ");
        consulta.append(" order by planoSaude.descricao ");
        var query = entityManager.createQuery(consulta.toString(), PlanoSaude.class);
        if(page != null && pageSize != null) {
            query.setFirstResult(page * pageSize);
            query.setMaxResults(pageSize);
        }
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
        PlanoSaude planoSaude = entityManager.find(PlanoSaude.class,idPlanoSaude);
        if(planoSaude == null)
            throw new NoObjectFoundException("Plano Saúde");
        return planoSaude;
    }

    @Override
    public void delete(PlanoSaude planoSaude) {
        entityManager.remove(planoSaude);
    }

    @Override
    public Long countPlanosSaude() {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from PlanoSaude planoSaude ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        return query.getSingleResult();
    }
}
