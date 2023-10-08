package com.medlinked.repositories.secretaria_repository;

import com.medlinked.entities.Secretaria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class SecretariaRepositoryClass implements SecretariaRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Secretaria saveSecretaria(Secretaria secretaria) {
        entityManager.persist(secretaria);
        return secretaria;
    }
}
