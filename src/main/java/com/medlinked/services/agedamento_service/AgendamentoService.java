package com.medlinked.services.agedamento_service;

import com.medlinked.entities.Agendamento;
import com.medlinked.entities.dtos.AgendamentoDto;

import java.util.List;

public interface AgendamentoService {
    Agendamento createAgendamento(AgendamentoDto agendamentoDto);

    Agendamento updateAgendamento(AgendamentoDto agendamentoDto, Integer idAgendamento);

    List<Agendamento> getAllAgendamentosMedicosSecretaria(Integer idSecretaria, Integer idMedico, Integer idPacientes);

    void deleteAgendamento(Integer idAgendamento);

    void deleteAllAgendamentosMedico(Integer idMedico);
}
