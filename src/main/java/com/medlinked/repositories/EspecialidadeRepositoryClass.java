package com.medlinked.repositories;

import com.medlinked.entities.Especialidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EspecialidadeRepositoryClass implements EspecialidadeRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Especialidade> getAllEspecialidades() {
        StringBuilder consulta = new StringBuilder(" select especialidade from Especialidade especialidade ");
        var query = entityManager.createQuery(consulta.toString(), Especialidade.class);
        return query.getResultList();
    }
}
