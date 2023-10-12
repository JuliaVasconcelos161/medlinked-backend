package com.medlinked.repositories.tipoplanosaude_repository;

import com.medlinked.entities.TipoPlanoSaude;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoPlanoSaudeRepositoryClass implements TipoPlanoSaudeRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<TipoPlanoSaude> getAllTiposPlanoSaude() {
        StringBuilder consulta = new StringBuilder(" select tipoPlanoSaude from TipoPlanoSaude tipoPlanoSaude ");
        var query = entityManager.createQuery(consulta.toString(), TipoPlanoSaude.class);
        return query.getResultList();
    }

    @Override
    public TipoPlanoSaude getOneTipoPlanoSaude(Integer idTipoPlanoSaude) {
        TipoPlanoSaude tipoPlanoSaude = entityManager.find(TipoPlanoSaude.class, idTipoPlanoSaude);
        if(tipoPlanoSaude == null)
            throw new NoObjectFoundException("Tipo Plano Saúde");
        return tipoPlanoSaude;
    }
}
