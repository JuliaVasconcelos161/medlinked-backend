package com.medlinked.repositories;

import com.medlinked.entities.Endereco;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class EnderecoRepositoryClass implements EnderecoRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Endereco saveEndereco(Endereco endereco) {
        entityManager.persist(endereco);
        return endereco;
    }
}
