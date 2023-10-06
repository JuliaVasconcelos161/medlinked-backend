package com.medlinked.repositories;

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
        try{
            return entityManager.find(Endereco.class, idPaciente);
        }catch (NoResultException e) {
            throw new NoObjectFoundException("Endereco");
        }
    }
}
