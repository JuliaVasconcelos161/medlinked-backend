package com.medlinked.repositories;

import com.medlinked.entities.Medico;
import com.medlinked.entities.PlanoSaude;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicoRepositoryClass implements MedicoRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public Medico saveMedico(Medico medico) {
        entityManager.persist(medico);
        return medico;
    }

    @Override
    public List<Medico> getAllMedicos() {
        StringBuilder consulta = new StringBuilder("select medico from Medico medico");
        var query = entityManager.createQuery(consulta.toString(), Medico.class);
        return query.getResultList();
    }

    @Override
    public Medico getOneMedico(Integer idMedico) {
        try{
            return entityManager.find(Medico.class, idMedico);
        } catch (NoResultException e) {
            throw new NoObjectFoundException("Médico");
        }

    }

    @Override
    public List<PlanoSaude> getPlanosSaudeMedico(Integer idMedico) {
        StringBuilder consulta = new StringBuilder(" select plano from Medico medico ");
        consulta.append(" inner join medico.planosSaude plano ");
        consulta.append(" where medico.idMedico = :ID ");
        var query = entityManager.createQuery(consulta.toString(), PlanoSaude.class);
        query.setParameter("ID", idMedico);
        return query.getResultList();
    }



    @Override
    public boolean existsMedicoByCpf(String cpf) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from Medico medico ");
        consulta.append(" where medico.pessoa.cpf = :CPF ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        query.setParameter("CPF", cpf);
        return query.getSingleResult() > 0;
    }

    @Override
    public Medico updateMedico(Medico medico) {
        entityManager.merge(medico);
        entityManager.flush();
        return medico;
    }


}
