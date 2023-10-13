package com.medlinked.repositories.medico_repository;

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
        consulta.append(" order by medico.pessoa.nome ");
        var query = entityManager.createQuery(consulta.toString(), Medico.class);
        return query.getResultList();
    }

    @Override
    public Medico getOneMedico(Integer idMedico) {
        Medico medico = entityManager.find(Medico.class, idMedico);
        if(medico == null)
            throw new NoObjectFoundException("Médico");
        return medico;

    }


    @Override
    public Medico updateMedico(Medico medico) {
        entityManager.merge(medico);
        entityManager.flush();
        return medico;
    }


}
