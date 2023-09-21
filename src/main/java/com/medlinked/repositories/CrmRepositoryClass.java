package com.medlinked.repositories;

import com.medlinked.entities.CRM;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CrmRepositoryClass implements CrmRepository {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public CRM saveCrm(CRM crm) {
        entityManager.persist(crm);
        return crm;
    }
}
