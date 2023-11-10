package com.medlinked.services.agendamento_service;

import com.medlinked.entities.dtos.AgendamentoAutomaticoDto;
import com.medlinked.entities.dtos.AgendamentoAutomaticoFalhoDto;

import java.util.List;

public interface AgendamentoAutomaticoService {
    List<AgendamentoAutomaticoFalhoDto> createAgendamentosAutomaticos(AgendamentoAutomaticoDto agendamentoAutomaticoDto);
}
