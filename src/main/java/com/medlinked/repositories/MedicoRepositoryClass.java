package com.medlinked.repositories;

import com.medlinked.entities.Medico;
import com.medlinked.exceptions.NoObjectFound;
import jakarta.persistence.EntityManager;
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
    public Medico getOneMedico(Long idMedico) {
        Medico medico = entityManager.find(Medico.class, idMedico);
        if(medico == null)
            throw new NoObjectFound();
        return medico;
    }

    @Override
    public boolean existsMedicoByCpf(Long cpf) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from Medico medico ");
        consulta.append(" where medico.pessoa.cpf = :CPF ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        query.setParameter("CPF", cpf);
        return query.getSingleResult() > 0;
    }


}
