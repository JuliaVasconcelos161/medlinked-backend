package com.medlinked.repositories.paciente_repository;

import com.medlinked.entities.Paciente;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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

    @Override
    public Paciente getOnePaciente(Integer idPaciente) {
        try{
            return entityManager.find(Paciente.class, idPaciente);
        } catch (NoResultException e) {
            throw new NoObjectFoundException("Paciente");
        }
    }

    @Override
    public Paciente updatePaciente(Paciente paciente) {
        entityManager.merge(paciente);
        entityManager.flush();
        return paciente;
    }
}
