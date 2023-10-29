package com.medlinked.repositories.especialidade_repository;

import com.medlinked.entities.Especialidade;
import com.medlinked.exceptions.NoObjectFoundException;
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
        consulta.append(" order by especialidade.descricao ");
        var query = entityManager.createQuery(consulta.toString(), Especialidade.class);
        return query.getResultList();
    }

    @Override
    public Especialidade getOneEspecialidade(Integer idEspecialidade) {
        Especialidade especialidade = entityManager.find(Especialidade.class, idEspecialidade);
        if(especialidade == null)
            throw new NoObjectFoundException("Especialidade");
        return especialidade;
    }
}
