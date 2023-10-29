package com.medlinked.services.agendamento_service;

import com.medlinked.entities.Agendamento;
import com.medlinked.entities.dtos.AgendamentoDto;

import java.util.List;

public interface AgendamentoService {
    Agendamento createAgendamento(AgendamentoDto agendamentoDto);

    Agendamento updateAgendamento(AgendamentoDto agendamentoDto, Integer idAgendamento);

    List<Agendamento> getAllAgendamentosMedicosSecretaria(Integer idSecretaria, Integer idMedico,
                                                          Integer idPaciente, Integer mes, Integer ano,
                                                          Integer dia);

    void deleteAgendamento(Integer idAgendamento);

    void deleteAllAgendamentosMedico(Integer idMedico);

    void deleteAllAgendamentosPaciente(Integer idPaciente);
}
