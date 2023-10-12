package com.medlinked.repositories.endereco_repository;

import com.medlinked.entities.Endereco;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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

    @Override
    public Endereco getOneEndereco(Integer idPaciente) {
        Endereco endereco = entityManager.find(Endereco.class, idPaciente);
        if(endereco == null)
            throw new NoObjectFoundException("Endereço");
        return endereco;
    }

    @Override
    public Endereco updateEndereco(Endereco endereco) {
        entityManager.merge(endereco);
        entityManager.flush();
        return endereco;
    }
}
