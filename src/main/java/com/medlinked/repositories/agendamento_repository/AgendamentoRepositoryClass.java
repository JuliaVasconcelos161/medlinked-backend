package com.medlinked.repositories.agendamento_repository;

import com.medlinked.entities.Agendamento;
import com.medlinked.enums.TipoAgendamento;
import com.medlinked.exceptions.ExistsException;
import com.medlinked.exceptions.NoObjectFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.lang3.BooleanUtils;
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
    public void validateHorarioAgendamentoExistente(String dataHoraInicioAgendamento, String dataHoraFimAgendamento,
                                                    Integer idMedico, Integer idAgendamento) {
        var query = entityManager.createQuery(this.consultaValidateHorarioAgendamentoExistente(
                dataHoraInicioAgendamento,dataHoraFimAgendamento, idAgendamento), Long.class);
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
                                                                 Integer idPaciente, Integer mes, Integer ano,
                                                                 Integer dia, TipoAgendamento tipoAgendamento,
                                                                 Integer page, Integer pageSize) {
        var query = entityManager.createQuery(this.consultaGetAllAgendamentosMedicosSecretaria(
                idMedico,idPaciente,mes,ano, dia, tipoAgendamento, false), Agendamento.class);
        if(idMedico != null)
            query.setParameter("IDMEDICO", idMedico);
        if(idPaciente != null)
            query.setParameter("IDPACIENTE", idPaciente);
        if(mes != null)
            query.setParameter("MES", mes);
        if(ano != null)
            query.setParameter("ANO", ano);
        if(dia != null)
            query.setParameter("DIA", dia);
        if(tipoAgendamento != null)
            query.setParameter("TIPOAGENDAMENTO", tipoAgendamento);
        query.setParameter("IDSECRETARIA", idSecretaria);
        if(page != null && pageSize != null) {
            query.setFirstResult(page * pageSize);
            query.setMaxResults(pageSize);
        }
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

    @Override
    public void deleteAllAgendamentosPaciente(Integer idPaciente) {
        StringBuilder consulta = new StringBuilder(" delete from Agendamento a ");
        consulta.append(" where a.paciente.idPaciente = :IDPACIENTE ");
        var query = entityManager.createQuery(consulta.toString());
        query.setParameter("IDPACIENTE", idPaciente);
        query.executeUpdate();
    }

    @Override
    public Long countGetAllAgendamentosMedicosSecretaria(Integer idSecretaria, Integer idMedico,
                                                         Integer idPaciente, Integer mes, Integer ano,
                                                         Integer dia, TipoAgendamento tipoAgendamento) {
        var query = entityManager.createQuery(this.consultaGetAllAgendamentosMedicosSecretaria(
                idMedico,idPaciente,mes,ano, dia, tipoAgendamento, true), Long.class);
        if(idMedico != null)
            query.setParameter("IDMEDICO", idMedico);
        if(idPaciente != null)
            query.setParameter("IDPACIENTE", idPaciente);
        if(mes != null)
            query.setParameter("MES", mes);
        if(ano != null)
            query.setParameter("ANO", ano);
        if(dia != null)
            query.setParameter("DIA", dia);
        if(tipoAgendamento != null)
            query.setParameter("TIPOAGENDAMENTO", tipoAgendamento);
        query.setParameter("IDSECRETARIA", idSecretaria);
        return query.getSingleResult();
    }

    @Override
    public void updataAgendamentosRemovePlanoSaude(Integer idPlanoSaude) {
        StringBuilder consulta = new StringBuilder(" update ");
        consulta.append(" Agendamento a ");
        consulta.append(" set a.planoSaude = null ");
        consulta.append(" where a.planoSaude.idPlanoSaude = :IDPLANOSAUDE");
        var query = entityManager.createQuery(consulta.toString());
        query.setParameter("IDPLANOSAUDE", idPlanoSaude);
        query.executeUpdate();
    }

    private String consultaValidateHorarioAgendamentoExistente(String dataHoraInicioAgendamento, String dataHoraFimAgendamento,
                                                               Integer idAgendamento) {
        StringBuilder consulta = new StringBuilder(" select count(1) ");
        consulta.append(" from Agendamento a ");
        consulta.append(" where (('");
        consulta.append(dataHoraInicioAgendamento);
        consulta.append("' between a.dataHoraInicioAgendamento and a.dataHoraFimAgendamento ");
        consulta.append(" or '");
        consulta.append(dataHoraFimAgendamento);
        consulta.append("' between a.dataHoraInicioAgendamento and a.dataHoraFimAgendamento) ");
        consulta.append(" or (a.dataHoraInicioAgendamento ");
        consulta.append(" between '");
        consulta.append(dataHoraInicioAgendamento);
        consulta.append("' and '");
        consulta.append(dataHoraFimAgendamento);
        consulta.append("' or a.dataHoraFimAgendamento");
        consulta.append(" between '");
        consulta.append(dataHoraInicioAgendamento);
        consulta.append("' and '");
        consulta.append(dataHoraFimAgendamento);
        consulta.append("')) and");
        consulta.append(" a.medico.idMedico = :IDMEDICO ");
        if(idAgendamento != null)
            consulta.append(" and a.idAgendamento != :IDAGENDAMENTO ");
        return consulta.toString();
    }

    private String consultaGetAllAgendamentosMedicosSecretaria(Integer idMedico, Integer idPaciente,
                                                               Integer mes, Integer ano, Integer dia,
                                                               TipoAgendamento tipoAgendamento, Boolean isCount) {
        StringBuilder consulta = new StringBuilder(" select  ");
        if(BooleanUtils.isTrue(isCount))
            consulta.append(" count(1) ");
        else
            consulta.append(" a ");
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
        if(mes != null)
            consulta.append(" and month(a.dataHoraInicioAgendamento) = :MES ");
        if(ano != null)
            consulta.append(" and year(a.dataHoraInicioAgendamento) = :ANO ");
        if(dia != null)
            consulta.append(" and day(a.dataHoraInicioAgendamento) = :DIA ");
        if(tipoAgendamento != null)
            consulta.append(" and a.tipoAgendamento = :TIPOAGENDAMENTO");
        if(BooleanUtils.isFalse(isCount))
            consulta.append(" order by a.dataHoraInicioAgendamento ");
        return consulta.toString();
    }
}
