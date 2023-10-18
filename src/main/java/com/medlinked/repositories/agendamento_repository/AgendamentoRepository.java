package com.medlinked.repositories.agendamento_repository;

import com.medlinked.entities.Agendamento;

public interface AgendamentoRepository {
    Agendamento saveAgendamento(Agendamento agendamento);

    void validateHorarioAgendamento(String dataHoraInicioAgendamento, String dataHoraFimAgendamento);
}
