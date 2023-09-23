package com.medlinked.repositories;

import com.medlinked.entities.Estado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstadoRepositoryClass implements EstadoRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Estado> getAllEstados() {
        StringBuilder consulta = new StringBuilder(" select estado from Estado estado ");
        var query = entityManager.createQuery(consulta.toString(), Estado.class);
        return query.getResultList();
    }
}
