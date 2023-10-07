package com.medlinked.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PlanoSaudePacienteRepositoryClass implements PlanoSaudePacienteRepository {

    @PersistenceContext
    EntityManager entityManager;
}
