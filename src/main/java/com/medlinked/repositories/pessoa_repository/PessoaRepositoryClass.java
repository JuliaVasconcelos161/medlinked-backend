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
        Pessoa pessoa = entityManager.find(Pessoa.class, idPessoa);
        if(pessoa == null)
            throw new NoObjectFoundException("Pessoa");
        return pessoa;
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
    public boolean existsEspecializacaoPessoaByCpf(String cpf, String especializacaoPessoa, Integer idPessoa) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from ");
        consulta.append(especializacaoPessoa);
        consulta.append(" especializacaoPessoa ");
        if(especializacaoPessoa.equals("Pessoa"))
            consulta.append(" where especializacaoPessoa.cpf = :CPF ");
        else
            consulta.append(" where especializacaoPessoa.pessoa.cpf = :CPF ");
        if(idPessoa != null) {
            consulta.append(" and especializacaoPessoa.id");
            consulta.append(especializacaoPessoa);
            consulta.append(" != :IDPESSOA ");
        }
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        if(idPessoa != null)
            query.setParameter("IDPESSOA", idPessoa);
        query.setParameter("CPF", cpf);
        return query.getSingleResult() > 0;
    }

    @Override
    public boolean existsEspecializacaoPessoaByEmail(String email, String especializacaoPessoa, Integer idPessoa) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from ");
        consulta.append(especializacaoPessoa);
        consulta.append(" especializacaoPessoa ");
        if(especializacaoPessoa.equals("Pessoa"))
            consulta.append(" where especializacaoPessoa.email = :EMAIL ");
        else
            consulta.append("  where especializacaoPessoa.pessoa.email = :EMAIL ");
        if(idPessoa != null) {
            consulta.append(" and especializacaoPessoa.id");
            consulta.append(especializacaoPessoa);
            consulta.append(" != :IDPESSOA ");
        }
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        if(idPessoa != null)
            query.setParameter("IDPESSOA", idPessoa);
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

    @Override
    public boolean existsPessoa(Integer idPessoa) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from Pessoa pessoa ");
        consulta.append(" where not exists( select medico ");
        consulta.append("   from Medico medico ");
        consulta.append("   where medico.idMedico = :IDPESSOA) ");
        consulta.append("and not exists( select secretaria ");
        consulta.append("   from Secretaria secretaria ");
        consulta.append("   where secretaria.idSecretaria = :IDPESSOA) ");
        consulta.append("and not exists( select paciente ");
        consulta.append("   from Paciente paciente ");
        consulta.append("   where paciente.idPaciente = :IDPESSOA) ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        query.setParameter("IDPESSOA", idPessoa);
        return query.getSingleResult() > 0;
    }

    @Override
    public void deletePessoa(Pessoa pessoa) {
        entityManager.remove(pessoa);
    }
}
