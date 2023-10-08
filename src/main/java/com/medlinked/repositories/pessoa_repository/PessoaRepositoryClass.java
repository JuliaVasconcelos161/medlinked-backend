package com.medlinked.repositories.pessoa_repository;

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

    @Override
    public Pessoa save(Pessoa pessoa) {
        entityManager.persist(pessoa);
        return pessoa;
    }

    @Override
    public boolean existsEspecializacaoPessoaByCpf(String cpf, String especializacaoPessoa) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from ");
        consulta.append(especializacaoPessoa);
        if(especializacaoPessoa.equals("Pessoa"))
            consulta.append(" pessoa ");
        else {
            consulta.append(" especializacaoPessoa ");
            consulta.append(" inner join especializacaoPessoa.pessoa pessoa ");
        }
        consulta.append(" where pessoa.cpf = :CPF ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        query.setParameter("CPF", cpf);
        return query.getSingleResult() > 0;
    }

    @Override
    public boolean existsEspecializacaoPessoaByEmail(String email, String especializacaoPessoa) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from ");
        consulta.append(especializacaoPessoa);
        if(especializacaoPessoa.equals("Pessoa"))
           consulta.append(" pessoa ");
        else {
            consulta.append(" especializacaoPessoa ");
            consulta.append(" inner join especializacaoPessoa.pessoa pessoa ");
        }
        consulta.append(" where pessoa.email = :EMAIL ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        query.setParameter("EMAIL", email);
        return query.getSingleResult() > 0;
    }

    @Override
    public Pessoa returnPessoaByCpf(String cpf) {
        StringBuilder consulta = new StringBuilder(" select pessoa ");
        consulta.append(" from Pessoa pessoa ");
        consulta.append(" where pessoa.cpf = :CPF ");
        var query = entityManager.createQuery(consulta.toString(), Pessoa.class);
        query.setParameter("CPF", cpf);
        try{
            return query.getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
    }
}
