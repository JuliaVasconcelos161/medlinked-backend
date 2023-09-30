package com.medlinked.repositories;

import com.medlinked.entities.Pessoa;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaRepositoryClass implements PessoaRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Pessoa getOnePessoa(Integer idPessoa) {
        try {
            return entityManager.find(Pessoa.class, idPessoa);
        } catch (NoResultException e) {
            throw new NoObjectFoundException("Pessoa");
        }
    }
    @Override
    public Pessoa updatePessoa(Pessoa pessoa) {
        entityManager.merge(pessoa);
        entityManager.flush();
        return pessoa;
    }
}
