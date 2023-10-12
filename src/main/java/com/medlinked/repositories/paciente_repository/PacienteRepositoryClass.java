package com.medlinked.repositories.paciente_repository;

import com.medlinked.entities.Paciente;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Set;

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
        Paciente paciente = entityManager.find(Paciente.class, idPaciente);
        if(paciente == null)
            throw new NoObjectFoundException("Paciente");
        return paciente;
    }

    @Override
    public Paciente updatePaciente(Paciente paciente) {
        entityManager.merge(paciente);
        entityManager.flush();
        return paciente;
    }

    @Override
    public Set<Paciente> getAllPacientes(String nomePaciente, String cpf) {
        StringBuilder consulta = new StringBuilder(" select paciente ");
        consulta.append(" from Paciente paciente ");
        consulta.append(" where ");

    }
}
