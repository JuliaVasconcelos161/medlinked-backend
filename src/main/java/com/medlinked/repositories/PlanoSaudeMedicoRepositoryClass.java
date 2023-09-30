package com.medlinked.repositories;

import com.medlinked.entities.Medico;
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
}
