package com.medlinked.repositories;

import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.Especialidade;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicoCrmRepositoryClass implements MedicoCrmRepository {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional
    public MedicoCRM saveCrm(MedicoCRM medicoCrm) {
        entityManager.persist(medicoCrm);
        return medicoCrm;
    }

    @Override
    public MedicoCRM getOneCrmByMedico(Integer idMedico) {
        try{
            return entityManager.find(MedicoCRM.class, idMedico);
        }catch (NoResultException e) {
            throw  new NoObjectFoundException("CRM");
        }
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
