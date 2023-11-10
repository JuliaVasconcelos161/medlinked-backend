package com.medlinked.repositories.agendamento_repository;

import com.medlinked.entities.Agendamento;

import java.util.List;

public interface AgendamentoRepository {
    Agendamento saveAgendamento(Agendamento agendamento);

    void validateHorarioAgendamentoExistente(String dataHoraInicioAgendamento, String dataHoraFimAgendamento,
                                             Integer idMedico, Integer idAgendamento);

    Agendamento getOneAgendamento(Integer idAgendamento);

    Agendamento updateAgendamento(Agendamento agendamento);

    List<Agendamento> getAllAgendamentosMedicosSecretaria(
            Integer idSecretaria, Integer idMedico, Integer idPaciente, Integer mes, Integer ano, Integer dia);

    void deleteAgendamento(Agendamento agendamento);

    void deleteAllAgendamentosMedico(Integer idMedico);

    void deleteAllAgendamentosPaciente(Integer idPaciente);
}
