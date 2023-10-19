package com.medlinked.services.agedamento_service;

import com.medlinked.entities.*;
import com.medlinked.entities.dtos.AgendamentoDto;
import com.medlinked.exceptions.AgendamentoException;
import com.medlinked.repositories.agendamento_repository.AgendamentoRepository;
import com.medlinked.repositories.medico_repository.MedicoRepository;
import com.medlinked.repositories.paciente_repository.PacienteRepository;
import com.medlinked.repositories.planosaude_repository.PlanoSaudeRepository;
import com.medlinked.repositories.secretaria_repository.SecretariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.medlinked.utils.JavaDateFormatter.FORMATTER;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    private final SecretariaRepository secretariaRepository;

    private final MedicoRepository medicoRepository;

    private final PacienteRepository pacienteRepository;

    private final PlanoSaudeRepository planoSaudeRepository;

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoServiceImpl(SecretariaRepository secretariaRepository, MedicoRepository medicoRepository,
                                  PacienteRepository pacienteRepository, PlanoSaudeRepository planoSaudeRepository,
                                  AgendamentoRepository agendamentoRepository) {
        this.secretariaRepository = secretariaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.planoSaudeRepository = planoSaudeRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    @Transactional
    @Override
    public Agendamento createAgendamento(AgendamentoDto agendamentoDto) {
        this.validateHorarioAgendamento(agendamentoDto.getDataHoraInicioAgendamento(),
                agendamentoDto.getDataHoraFimAgendamento(), agendamentoDto.getIdMedico());
        Secretaria secretaria = secretariaRepository.getOneSecretaria(agendamentoDto.getIdSecretaria());
        Medico medico = medicoRepository.getOneMedico(agendamentoDto.getIdMedico());
        Paciente paciente = pacienteRepository.getOnePaciente(agendamentoDto.getIdPaciente());
        PlanoSaude planoSaude = agendamentoDto.getIdPlanoSaude() != null ?
                planoSaudeRepository.getOnePlanoSaude(agendamentoDto.getIdPlanoSaude())
                : null;
        Agendamento agendamento = Agendamento.builder()
                .tipoAgendamento(agendamentoDto.getTipoAgendamento())
                .dataHoraInicioAgendamento(LocalDateTime.parse(agendamentoDto.getDataHoraInicioAgendamento(), FORMATTER))
                .dataHoraFimAgendamento(LocalDateTime.parse(agendamentoDto.getDataHoraFimAgendamento(), FORMATTER))
                .descricao(agendamentoDto.getDescricao())
                .secretaria(secretaria)
                .medico(medico)
                .paciente(paciente)
                .planoSaude(planoSaude)
                .build();
        return agendamentoRepository.saveAgendamento(agendamento);
    }

    @Transactional
    @Override
    public Agendamento updateAgendamento(AgendamentoDto agendamentoDto, Integer idAgendamento) {
        this.validateHorarioAgendamento(agendamentoDto.getDataHoraInicioAgendamento(),
                agendamentoDto.getDataHoraFimAgendamento(), agendamentoDto.getIdMedico());
        Agendamento agendamento = agendamentoRepository.getOneAgendamento(idAgendamento);
        agendamento.setTipoAgendamento(agendamentoDto.getTipoAgendamento());
        agendamento.setDataHoraInicioAgendamento(LocalDateTime.parse(agendamentoDto.getDataHoraInicioAgendamento(), FORMATTER));
        agendamento.setDataHoraFimAgendamento(LocalDateTime.parse(agendamentoDto.getDataHoraFimAgendamento(), FORMATTER));
        agendamento.setDescricao(agendamentoDto.getDescricao());
        if(!agendamento.getSecretaria().getIdSecretaria().equals(agendamentoDto.getIdSecretaria())) {
            Secretaria secretaria = secretariaRepository.getOneSecretaria(agendamentoDto.getIdSecretaria());
            agendamento.setSecretaria(secretaria);
        }
        if(!agendamento.getMedico().getIdMedico().equals(agendamentoDto.getIdMedico())) {
            Medico medico = medicoRepository.getOneMedico(agendamentoDto.getIdMedico());
            agendamento.setMedico(medico);
        }
        if(!agendamento.getPaciente().getIdPaciente().equals(agendamentoDto.getIdPaciente())) {
            Paciente paciente = pacienteRepository.getOnePaciente(agendamentoDto.getIdPaciente());
            agendamento.setPaciente(paciente);
        }
        if(!agendamento.getPlanoSaude().getIdPlanoSaude().equals(agendamentoDto.getIdPlanoSaude())) {
            PlanoSaude planoSaude = agendamentoDto.getIdPlanoSaude() != null ?
                    planoSaudeRepository.getOnePlanoSaude(agendamentoDto.getIdPlanoSaude())
                    : null;
            agendamento.setPlanoSaude(planoSaude);
        }
        return agendamentoRepository.updateAgendamento(agendamento);
    }

    private void validateHorarioAgendamento(String dataHoraInicioAgendamento, String dataHoraFimAgendamento, Integer idMedico) {
        LocalDateTime inicio = LocalDateTime.parse(dataHoraInicioAgendamento, FORMATTER);
        LocalDateTime fim = LocalDateTime.parse(dataHoraFimAgendamento, FORMATTER);
        if(inicio.isAfter(fim))
            throw new AgendamentoException();
        agendamentoRepository.validateHorarioAgendamento(dataHoraInicioAgendamento, dataHoraFimAgendamento, idMedico);
    }
}
