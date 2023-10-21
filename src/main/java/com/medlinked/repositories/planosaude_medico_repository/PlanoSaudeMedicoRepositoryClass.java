package com.medlinked.repositories.planosaude_medico_repository;

import com.medlinked.entities.PlanoSaude;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanoSaudeMedicoRepositoryClass implements PlanoSaudeMedicoRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PlanoSaude> getAllPlanosSaudeMedico(Integer idMedico, Integer page, Integer pageSize) {
        StringBuilder consulta = new StringBuilder(" select planoSaude ");
        consulta.append(" from PlanoSaude planoSaude ");
        consulta.append(" inner join planoSaude.medicos medico ");
        consulta.append(" where medico.idMedico = :IDMEDICO ");
        consulta.append(" order by planoSaude.descricao ");
        var query = entityManager.createQuery(consulta.toString(), PlanoSaude.class);
        query.setParameter("IDMEDICO", idMedico);
        if(page != null && pageSize != null) {
            query.setFirstResult(page * pageSize);
            query.setMaxResults(pageSize);
        }
        return query.getResultList();
    }

    @Override
    public Long countPlanosSaudeMedico(Integer idMedico) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from PlanoSaude planoSaude ");
        consulta.append(" inner join planoSaude.medicos medico ");
        consulta.append(" where medico.idMedico = :IDMEDICO ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        query.setParameter("IDMEDICO", idMedico);
        return query.getSingleResult();
    }
}
