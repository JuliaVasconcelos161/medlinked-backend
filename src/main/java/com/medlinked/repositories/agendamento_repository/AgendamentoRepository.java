package com.medlinked.repositories.agendamento_repository;

import com.medlinked.entities.Agendamento;

import java.util.List;

public interface AgendamentoRepository {
    Agendamento saveAgendamento(Agendamento agendamento);

    void validateHorarioAgendamento(String dataHoraInicioAgendamento, String dataHoraFimAgendamento,
                                    Integer idMedico, Integer idAgendamento);

    Agendamento getOneAgendamento(Integer idAgendamento);

    Agendamento updateAgendamento(Agendamento agendamento);

    List<Agendamento> getAllAgendamentosMedicosSecretaria(Integer idSecretaria);
}
