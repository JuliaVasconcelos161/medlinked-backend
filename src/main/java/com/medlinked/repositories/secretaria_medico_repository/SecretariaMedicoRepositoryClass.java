package com.medlinked.repositories.secretaria_medico_repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class SecretariaMedicoRepositoryClass implements SecretariaMedicoRepository {
    @PersistenceContext
    EntityManager entityManager;
}
