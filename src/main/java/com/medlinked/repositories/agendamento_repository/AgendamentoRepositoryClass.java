package com.medlinked.repositories.agendamento_repository;

import com.medlinked.entities.Agendamento;
import com.medlinked.exceptions.ExistsException;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AgendamentoRepositoryClass implements AgendamentoRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Agendamento saveAgendamento(Agendamento agendamento) {
        entityManager.persist(agendamento);
        return agendamento;
    }

    @Override
    public void validateHorarioAgendamento(String dataHoraInicioAgendamento, String dataHoraFimAgendamento,
                                           Integer idMedico, Integer idAgendamento) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from Agendamento a ");
        consulta.append(" where ('");
        consulta.append(dataHoraInicioAgendamento);
        consulta.append("' between a.dataHoraInicioAgendamento and a.dataHoraFimAgendamento ");
        consulta.append(" or '");
        consulta.append(dataHoraFimAgendamento);
        consulta.append("' between a.dataHoraInicioAgendamento and a.dataHoraFimAgendamento) ");
        consulta.append(" and a.medico.idMedico = :IDMEDICO ");
        if(idAgendamento != null)
            consulta.append(" and a.idAgendamento != :IDAGENDAMENTO ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        query.setParameter("IDMEDICO", idMedico);
        if(idAgendamento != null)
            query.setParameter("IDAGENDAMENTO", idAgendamento);
        if(query.getSingleResult() > 0)
            throw new ExistsException("Agendamento","Horário");
    }

    @Override
    public Agendamento getOneAgendamento(Integer idAgendamento) {
        Agendamento agendamento = entityManager.find(Agendamento.class, idAgendamento);
        if(agendamento == null)
            throw new NoObjectFoundException("Agendamento");
        return agendamento;
    }

    @Override
    public Agendamento updateAgendamento(Agendamento agendamento) {
        entityManager.merge(agendamento);
        entityManager.flush();
        return agendamento;
    }

    @Override
    public List<Agendamento> getAllAgendamentosMedicosSecretaria(Integer idSecretaria, Integer idMedico,
                                                                 Integer idPaciente) {
        StringBuilder consulta = new StringBuilder(" select a ");
        consulta.append(" from Agendamento a ");
        consulta.append(" inner join a.medico medico ");
        if(idPaciente != null)
            consulta.append(" inner join a.paciente paciente ");
        consulta.append(" where medico.idMedico in (");
        consulta.append("   select medico.idMedico ");
        consulta.append("   from Secretaria secretaria ");
        consulta.append("   inner join secretaria.medicos medico ");
        consulta.append("   where secretaria.idSecretaria = :IDSECRETARIA) ");
        if(idMedico != null)
            consulta.append(" and medico.idMedico = :IDMEDICO ");
        if(idPaciente != null)
            consulta.append(" and paciente.idPaciente = :IDPACIENTE ");
        consulta.append(" order by a.dataHoraInicioAgendamento ");
        var query = entityManager.createQuery(consulta.toString(), Agendamento.class);
        if(idMedico != null)
            query.setParameter("IDMEDICO", idMedico);
        if(idPaciente != null)
            query.setParameter("IDPACIENTE", idPaciente);
        query.setParameter("IDSECRETARIA", idSecretaria);
        return query.getResultList();
    }

    @Override
    public void deleteAgendamento(Agendamento agendamento) {
        entityManager.remove(agendamento);
    }

    @Override
    public void deleteAllAgendamentosMedico(Integer idMedico) {
        StringBuilder consulta = new StringBuilder(" delete from Agendamento a ");
        consulta.append(" where a.medico.idMedico = :IDMEDICO ");
        var query = entityManager.createQuery(consulta.toString());
        query.setParameter("IDMEDICO", idMedico);
        query.executeUpdate();
    }
}
