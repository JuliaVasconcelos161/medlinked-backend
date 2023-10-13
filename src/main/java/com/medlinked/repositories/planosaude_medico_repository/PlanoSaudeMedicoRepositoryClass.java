package com.medlinked.repositories.planosaude_medico_repository;

import com.medlinked.entities.Medico;
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
    public List<Medico> getAllMedicosPlanoSaude(Integer idPlanoSaude) {
        StringBuilder consulta = new StringBuilder(" select medico ");
        consulta.append(" from Medico medico ");
        consulta.append(" inner join medico.planosSaude planoSaude ");
        consulta.append(" where planoSaude.idPlanoSaude = :IDPLANOSAUDE ");
        var query = entityManager.createQuery(consulta.toString(), Medico.class);
        query.setParameter("IDPLANOSAUDE", idPlanoSaude);
        return query.getResultList();
    }

    @Override
    public List<PlanoSaude> getAllPlanosSaudeMedico(Integer idMedico, int page, int pageSize) {
        StringBuilder consulta = new StringBuilder(" select planoSaude ");
        consulta.append(" from PlanoSaude planoSaude ");
        consulta.append(" inner join planoSaude.medicos medico ");
        consulta.append(" where medico.idMedico = :IDMEDICO ");
        consulta.append(" order by planoSaude.descricao ");
        var query = entityManager.createQuery(consulta.toString(), PlanoSaude.class);
        query.setParameter("IDMEDICO", idMedico);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
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
