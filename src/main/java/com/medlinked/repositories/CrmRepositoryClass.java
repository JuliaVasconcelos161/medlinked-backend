package com.medlinked.repositories;

import com.medlinked.entities.CRM;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class CrmRepositoryClass implements CrmRepository {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional
    public CRM saveCrm(CRM crm) {
        entityManager.persist(crm);
        return crm;
    }


}
