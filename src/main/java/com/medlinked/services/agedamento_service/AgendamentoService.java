package com.medlinked.services.agedamento_service;

import com.medlinked.entities.Agendamento;
import com.medlinked.entities.dtos.AgendamentoDto;

public interface AgendamentoService {
    Agendamento createAgendamento(AgendamentoDto agendamentoDto);
}
