package com.medlinked.repositories.planosaude_paciente_repository;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.PlanoSaudePaciente;
import com.medlinked.entities.dtos.PlanoSaudePacienteResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanoSaudePacienteRepositoryClass implements PlanoSaudePacienteRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public PlanoSaudePaciente savePlanoSaudePaciente(PlanoSaudePaciente planoSaudePaciente) {
        entityManager.persist(planoSaudePaciente);
        return planoSaudePaciente;
    }

    @Override
    public void disassociatePacientePlanoSaude(Integer idPaciente, Integer idPlanoSaude) {
        StringBuilder consulta = new StringBuilder(" delete from PlanoSaudePaciente planoSaudePaciente ");
        consulta.append(" where planoSaudePaciente.idPlanoSaudePaciente.paciente.idPaciente = :IDPACIENTE ");
        consulta.append(" and planoSaudePaciente.idPlanoSaudePaciente.planoSaude.idPlanoSaude = :IDPLANOSAUDE ");
        var query = entityManager.createQuery(consulta.toString());
        query.setParameter("IDPACIENTE", idPaciente);
        query.setParameter("IDPLANOSAUDE", idPlanoSaude);
        query.executeUpdate();
    }

    @Override
    public void disassociateAllPacientesPlanoSaude(Integer idPlanoSaude) {
        StringBuilder consulta = new StringBuilder(" delete from PlanoSaudePaciente planoSaudePaciente ");
        consulta.append(" where planoSaudePaciente.idPlanoSaudePaciente.planoSaude.idPlanoSaude = :IDPLANOSAUDE ");
        var query = entityManager.createQuery(consulta.toString());
        query.setParameter("IDPLANOSAUDE", idPlanoSaude);
        query.executeUpdate();
    }

    @Override
    public List<PlanoSaudePacienteResponseDto> buildPlanoSaudePacienteResponseDto(Integer idPaciente) {
        StringBuilder consulta = new StringBuilder(" select new com.medlinked.entities.dtos.PlanoSaudePacienteResponseDto( ");
        consulta.append(" planoSaudePaciente.idPlanoSaudePaciente.planoSaude, ");
        consulta.append(" planoSaudePaciente.numeroCarteirinha, ");
        consulta.append(" tipoPlanoSaude) ");
        consulta.append(" from PlanoSaudePaciente planoSaudePaciente ");
        consulta.append(" inner join planoSaudePaciente.tipoPlanoSaude tipoPlanoSaude ");
        consulta.append(" where planoSaudePaciente.idPlanoSaudePaciente.paciente.idPaciente = :IDPACIENTE ");
        consulta.append(" order by planoSaudePaciente.idPlanoSaudePaciente.planoSaude.descricao ");
        var query = entityManager.createQuery(consulta.toString(), PlanoSaudePacienteResponseDto.class);
        query.setParameter("IDPACIENTE", idPaciente);
        return query.getResultList();
    }

    @Override
    public List<PlanoSaude> getAllPlanosSaudePacienteMedico(Integer idPaciente, Integer idMedico) {
        StringBuilder consulta = new StringBuilder(" select planoSaudePaciente.idPlanoSaudePaciente.planoSaude ");
        consulta.append(" from PlanoSaudePaciente planoSaudePaciente ");
        consulta.append(" inner join planoSaudePaciente.idPlanoSaudePaciente.planoSaude.medicos medico ");
        consulta.append(" where planoSaudePaciente.idPlanoSaudePaciente.paciente.idPaciente = :IDPACIENTE ");
        consulta.append(" and medico.idMedico = :IDMEDICO ");
        consulta.append(" order by planoSaudePaciente.idPlanoSaudePaciente.planoSaude.descricao ");
        var query = entityManager.createQuery(consulta.toString(), PlanoSaude.class);
        query.setParameter("IDPACIENTE", idPaciente);
        query.setParameter("IDMEDICO", idMedico);
        return query.getResultList();
    }

    @Override
    public void disassociateAllPlanosSaudePaciente(Integer idPaciente) {
        StringBuilder consulta = new StringBuilder(" delete from PlanoSaudePaciente planoSaudePaciente ");
        consulta.append(" where planoSaudePaciente.idPlanoSaudePaciente.paciente.idPaciente = :IDPACIENTE ");
        var query = entityManager.createQuery(consulta.toString());
        query.setParameter("IDPACIENTE", idPaciente);
        query.executeUpdate();
    }
}
