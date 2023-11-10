package com.medlinked.services.agendamento_service;

import com.medlinked.entities.Agendamento;
import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.AgendamentoAutomaticoDto;
import com.medlinked.entities.dtos.AgendamentoAutomaticoFalhoDto;
import com.medlinked.enums.TipoAgendamento;
import com.medlinked.exceptions.ExistsException;
import com.medlinked.repositories.agendamento_repository.AgendamentoRepository;
import com.medlinked.repositories.medico_repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgendamentoAutomaticoServiceImpl implements AgendamentoAutomaticoService {

    private final MedicoRepository medicoRepository;

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoAutomaticoServiceImpl(MedicoRepository medicoRepository, AgendamentoRepository agendamentoRepository) {
        this.medicoRepository = medicoRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    @Transactional
    @Override
    public List<AgendamentoAutomaticoFalhoDto> createAgendamentosAutomaticos(AgendamentoAutomaticoDto agendamentoAutomaticoDto) {
        Agendamento agendamento;
        Medico medico = medicoRepository.getOneMedico(agendamentoAutomaticoDto.getIdMedico());
        LocalDate dataInicio = LocalDate.parse(agendamentoAutomaticoDto.getDataInicioPreAgendamento());
        LocalDate dataFim = LocalDate.parse(agendamentoAutomaticoDto.getDataFimPreAgendamento());
        LocalTime horaInicio = LocalTime.parse(agendamentoAutomaticoDto.getHoraInicioGeracao());
        LocalTime horaFim = LocalTime.parse(agendamentoAutomaticoDto.getHoraFimGeracao());
        LocalDateTime diaHorarioAgendamento = LocalDateTime.of(dataInicio, horaInicio);
        List<AgendamentoAutomaticoFalhoDto> agendamentosFalhos = new ArrayList<>();
        while (diaHorarioAgendamento.isBefore(LocalDateTime.of(dataFim, horaFim))){
            while (diaHorarioAgendamento
                    .isBefore(LocalDateTime.of(diaHorarioAgendamento.toLocalDate(), horaFim))) {
                try{
                    agendamentoRepository
                            .validateHorarioAgendamentoExistente(
                                    diaHorarioAgendamento.plusMinutes(1).toString(),
                                    diaHorarioAgendamento.plusMinutes(agendamentoAutomaticoDto.getTempoIntervalo()-1)
                                            .toString(),
                                    medico.getIdMedico(), null);
                } catch (ExistsException e) {
                    AgendamentoAutomaticoFalhoDto agendamentoFalho =
                            new AgendamentoAutomaticoFalhoDto(diaHorarioAgendamento.toString(),
                                    diaHorarioAgendamento.plusMinutes(agendamentoAutomaticoDto.getTempoIntervalo()).toString());
                    agendamentosFalhos.add(agendamentoFalho);
                    diaHorarioAgendamento = diaHorarioAgendamento.plusMinutes(agendamentoAutomaticoDto.getTempoIntervalo());
                    continue;
                }
                agendamento = Agendamento.builder()
                        .dataHoraInicioAgendamento(diaHorarioAgendamento)
                        .dataHoraFimAgendamento(diaHorarioAgendamento.plusMinutes(agendamentoAutomaticoDto.getTempoIntervalo()))
                        .medico(medico)
                        .tipoAgendamento(TipoAgendamento.AUTOMATICO)
                        .build();
                agendamentoRepository.saveAgendamento(agendamento);
                diaHorarioAgendamento = diaHorarioAgendamento.plusMinutes(agendamentoAutomaticoDto.getTempoIntervalo());
            }
            if(agendamentoAutomaticoDto.getIsApenasDiasUteis() && diaHorarioAgendamento.getDayOfWeek() == DayOfWeek.FRIDAY)
                diaHorarioAgendamento = diaHorarioAgendamento.plusDays(3);
            else
                diaHorarioAgendamento = diaHorarioAgendamento.plusDays(1);
            diaHorarioAgendamento = diaHorarioAgendamento.withHour(horaInicio.getHour()).withMinute(horaInicio.getMinute());
        }
        return agendamentosFalhos;
    }

}
