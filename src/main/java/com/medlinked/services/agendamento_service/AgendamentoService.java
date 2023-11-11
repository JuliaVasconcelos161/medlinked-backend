package com.medlinked.services.agendamento_service;

import com.medlinked.entities.Agendamento;
import com.medlinked.entities.dtos.AgendamentoDto;
import com.medlinked.enums.TipoAgendamento;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AgendamentoService {
    Agendamento createAgendamento(AgendamentoDto agendamentoDto);

    Agendamento updateAgendamento(AgendamentoDto agendamentoDto, Integer idAgendamento);

    Page<Agendamento> getAllAgendamentosMedicosSecretariaPaginado(Integer idSecretaria, Integer idMedico,
                                                                  Integer idPaciente, Integer mes, Integer ano,
                                                                  Integer dia, TipoAgendamento tipoAgendamento,
                                                                  Integer page, Integer pageSize);

    List<Agendamento> getAllAgendamentosMedicosSecretaria(Integer idSecretaria, Integer idMedico,
                                                          Integer idPaciente, Integer mes, Integer ano,
                                                          Integer dia, TipoAgendamento tipoAgendamento);

    void deleteAgendamento(Integer idAgendamento);

    void deleteAllAgendamentosMedico(Integer idMedico);

    void deleteAllAgendamentosPaciente(Integer idPaciente);

    Agendamento getOneAgendamento(Integer idAgendamento);
}
