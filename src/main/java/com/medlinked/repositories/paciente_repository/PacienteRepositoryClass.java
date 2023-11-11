package com.medlinked.repositories.paciente_repository;

import com.medlinked.entities.Paciente;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PacienteRepositoryClass implements PacienteRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Paciente savePaciente(Paciente paciente) {
        entityManager.persist(paciente);
        return paciente;
    }

    @Override
    public Paciente getOnePaciente(Integer idPaciente) {
        Paciente paciente = entityManager.find(Paciente.class, idPaciente);
        if(paciente == null)
            throw new NoObjectFoundException("Paciente");
        return paciente;
    }

    @Override
    public Paciente updatePaciente(Paciente paciente) {
        entityManager.merge(paciente);
        entityManager.flush();
        return paciente;
    }

    @Override
    public List<Paciente> getAllPacientes(String nomePaciente, String cpf, Integer page, Integer pageSize) {
        var query = entityManager.createQuery(this.consultaGetAllPacientes(nomePaciente, cpf, false), Paciente.class);
        if(nomePaciente != null)
            query.setParameter("NOMEPACIENTE", "%"+nomePaciente+"%");
        if(cpf != null)
            query.setParameter("CPF", cpf);
        if(page != null && pageSize != null) {
            query.setFirstResult(page * pageSize);
            query.setMaxResults(pageSize);
        }
        return query.getResultList();
    }

    private String consultaGetAllPacientes(String nomePaciente, String cpf, Boolean isCount) {
        StringBuilder consulta = new StringBuilder(" select  ");
        if(BooleanUtils.isTrue(isCount))
            consulta.append(" count(1) ");
        else
            consulta.append(" paciente ");
        consulta.append(" from Paciente paciente ");
        consulta.append(" inner join paciente.pessoa pessoa ");
        if(nomePaciente != null) {
            consulta.append(" where pessoa.nome like :NOMEPACIENTE ");
            if(cpf != null)
                consulta.append(" and pessoa.cpf = :CPF");
        } else if(cpf != null)
            consulta.append(" where pessoa.cpf = :CPF ");
        if(BooleanUtils.isFalse(isCount))
            consulta.append(" order by pessoa.nome ");
        return consulta.toString();
    }

    @Override
    public Long countPacientes(String nomePaciente, String cpf) {
        var query = entityManager.createQuery(consultaGetAllPacientes(nomePaciente, cpf, true), Long.class);
        if(nomePaciente != null)
            query.setParameter("NOMEPACIENTE", "%"+nomePaciente+"%");
        if(cpf != null)
            query.setParameter("CPF", cpf);
        return query.getSingleResult();
    }

    @Override
    public void deletePaciente(Paciente paciente) {
        entityManager.remove(paciente);
    }

}
