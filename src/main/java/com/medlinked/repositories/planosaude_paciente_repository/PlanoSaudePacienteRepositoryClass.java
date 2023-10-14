package com.medlinked.repositories.planosaude_paciente_repository;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.PlanoSaudePaciente;
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
    public void desassociatePlanoSaudeAllPacientes(Integer idPlanoSaude) {
        StringBuilder consulta = new StringBuilder(" delete from PlanoSaudePaciente planoSaudePaciente ");
        consulta.append(" where planoSaudePaciente.idPlanoSaudePaciente.planoSaude.idPlanoSaude = :IDPLANOSAUDE ");
        var query = entityManager.createQuery(consulta.toString());
        query.setParameter("IDPLANOSAUDE", idPlanoSaude);
        query.executeUpdate();
    }

    @Override
    public List<PlanoSaude> getAllPlanosSaudePaciente(Integer idPaciente) {
        StringBuilder consulta = new StringBuilder(" select planoSaudePaciente.idPlanoSaudePaciente.planoSaude ");
        consulta.append(" from PlanoSaudePaciente planoSaudePaciente ");
        consulta.append(" where planoSaudePaciente.idPlanoSaudePaciente.paciente.idPaciente = :IDPACIENTE ");
        consulta.append(" order by planoSaudePaciente.idPlanoSaudePaciente.planoSaude.descricao ");
        var query = entityManager.createQuery(consulta.toString(), PlanoSaude.class);
        query.setParameter("IDPACIENTE", idPaciente);
        return query.getResultList();
    }
}
