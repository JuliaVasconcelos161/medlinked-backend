package com.medlinked.repositories.estado_repository;

import com.medlinked.entities.Estado;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
        consulta.append(" order by estado.descricao ");
        var query = entityManager.createQuery(consulta.toString(), Estado.class);
        return query.getResultList();
    }

    @Override
    public Estado getOneEstado(String uf) {
        Estado estado = entityManager.find(Estado.class, uf);
        if(estado == null)
            throw new NoObjectFoundException("Estado");
        return estado;
    }
}
