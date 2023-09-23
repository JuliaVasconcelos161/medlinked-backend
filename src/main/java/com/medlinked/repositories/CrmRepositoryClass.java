package com.medlinked.repositories;

import com.medlinked.entities.CRM;
import com.medlinked.entities.Especialidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CrmRepositoryClass implements CrmRepository {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional
    public CRM saveCrm(CRM crm) {
        entityManager.persist(crm);
        return crm;
    }

    @Override
    public CRM getOneMedicoByCrm(Integer idMedico) {
        StringBuilder consulta = new StringBuilder(" select new com.medlinked.entities.CRM(");
        consulta.append(" estado, crm.numeroCrm ");
        consulta.append(") from CRM crm ");
        consulta.append(" inner join crm.estado estado ");
        consulta.append(" where crm.idMedico = :ID ");
        var query = entityManager.createQuery(consulta.toString(), CRM.class);
        query.setParameter("ID", idMedico);
        return query.getSingleResult();
    }

    @Override
    public List<Especialidade> getEspecialidadesMedicoByCrm(Integer idMedico) {
        StringBuilder consulta = new StringBuilder(" select especialidade from CRM crm ");
        consulta.append(" inner join crm.especialidades especialidade ");
        consulta.append(" where crm.idMedico = :ID ");
        var query = entityManager.createQuery(consulta.toString(), Especialidade.class);
        query.setParameter("ID", idMedico);
        return query.getResultList();
    }
}
