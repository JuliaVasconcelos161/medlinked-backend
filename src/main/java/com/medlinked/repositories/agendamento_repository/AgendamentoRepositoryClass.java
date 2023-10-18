package com.medlinked.repositories.agendamento_repository;

import com.medlinked.entities.Agendamento;
import com.medlinked.exceptions.ExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

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
    public void validateHorarioAgendamento(String dataHoraInicioAgendamento, String dataHoraFimAgendamento) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from Agendamento a ");
        consulta.append(" where '");
        consulta.append(dataHoraInicioAgendamento);
        consulta.append("' between a.dataHoraInicioAgendamento and a.dataHoraFimAgendamento ");
        consulta.append(" or '");
        consulta.append(dataHoraFimAgendamento);
        consulta.append("' between a.dataHoraInicioAgendamento and a.dataHoraFimAgendamento ");
        var query = entityManager.createQuery(consulta.toString(), Long.class);
        if(query.getSingleResult() > 0)
            throw new ExistsException("Agendamento","Horário");
    }
}
