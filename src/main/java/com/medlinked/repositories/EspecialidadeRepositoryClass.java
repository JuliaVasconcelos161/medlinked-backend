package com.medlinked.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class EspecialidadeRepositoryClass implements EspecialidadeRepository {

    @PersistenceContext
    EntityManager entityManager;
}
