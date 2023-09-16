package com.medlinked.repositories;

import com.medlinked.entities.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicoRepositoryClass implements MedicoRepository{
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public Medico saveMedico(Medico medico) {
        entityManager.persist(medico);
        return medico;
    }

    @Override
    public List<Medico> getAllMedicos() {
        StringBuilder hql = new StringBuilder("select medico from Medico medico");
        var query = entityManager.createQuery(hql.toString(), Medico.class);
        return query.getResultList();
    }
}
