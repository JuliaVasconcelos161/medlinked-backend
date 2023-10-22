package com.medlinked.repositories.paciente_repository;

import com.medlinked.entities.Paciente;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    public List<Paciente> getAllPacientes(String nomePaciente, String cpf, int page, int pageSize) {
        var query = entityManager.createQuery(this.consultaGetAllPacientes(nomePaciente, cpf), Paciente.class);
        if(nomePaciente != null)
            query.setParameter("NOMEPACIENTE", "%"+nomePaciente+"%");
        if(cpf != null)
            query.setParameter("CPF", cpf);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    private String consultaGetAllPacientes(String nomePaciente, String cpf) {
        StringBuilder consulta = new StringBuilder(" select paciente ");
        consulta.append(" from Paciente paciente ");
        consulta.append(" inner join paciente.pessoa pessoa ");
        if(nomePaciente != null) {
            consulta.append(" where pessoa.nome like :NOMEPACIENTE ");
            if(cpf != null)
                consulta.append(" and pessoa.cpf = :CPF");
        } else if(cpf != null)
            consulta.append(" where pessoa.cpf = :CPF ");
        consulta.append(" order by pessoa.nome ");
        return consulta.toString();
    }

    @Override
    public Long countPacientes() {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from Paciente paciente ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        return query.getSingleResult();
    }

    @Override
    public void deletePaciente(Paciente paciente) {
        entityManager.remove(paciente);
    }

}
