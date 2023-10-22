package com.medlinked.repositories.secretaria_repository;

import com.medlinked.entities.Secretaria;
import com.medlinked.exceptions.NoObjectFoundException;
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

    @Override
    public Secretaria getOneSecretaria(Integer idSecretaria) {
        Secretaria secretaria = entityManager.find(Secretaria.class, idSecretaria);
        if(secretaria == null)
            throw new NoObjectFoundException("Secretária");
        return secretaria;
    }

    @Override
    public Secretaria updateSecretaria(Secretaria secretaria) {
        entityManager.merge(secretaria);
        entityManager.flush();
        return secretaria;
    }

    @Override
    public void deleteSecretaria(Secretaria secretaria) {
        entityManager.remove(secretaria);
    }
}
