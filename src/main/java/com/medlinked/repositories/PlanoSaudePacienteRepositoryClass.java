package com.medlinked.repositories;

import com.medlinked.entities.PlanoSaudePaciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PlanoSaudePacienteRepositoryClass implements PlanoSaudePacienteRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public PlanoSaudePaciente savePlanoSaudePaciente(PlanoSaudePaciente planoSaudePaciente) {
        entityManager.persist(planoSaudePaciente);
        return planoSaudePaciente;
    }
}
