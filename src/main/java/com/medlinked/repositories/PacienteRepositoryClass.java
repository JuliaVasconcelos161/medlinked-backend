package com.medlinked.repositories;

import com.medlinked.entities.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PacienteRepositoryClass implements PacienteRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Paciente savePaciente(Paciente paciente) {
        entityManager.persist(paciente);
        return paciente;
    }
}
