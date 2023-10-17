package com.medlinked.services.agedamento_service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.medlinked.entities.*;
import com.medlinked.entities.dtos.AgendamentoDto;
import com.medlinked.exceptions.NoObjectFoundException;
import com.medlinked.repositories.agendamento_repository.AgendamentoRepository;
import com.medlinked.repositories.medico_repository.MedicoRepository;
import com.medlinked.repositories.paciente_repository.PacienteRepository;
import com.medlinked.repositories.planosaude_repository.PlanoSaudeRepository;
import com.medlinked.repositories.secretaria_repository.SecretariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        Secretaria secretaria = secretariaRepository.getOneSecretaria(agendamentoDto.getIdSecretaria());
        Medico medico = medicoRepository.getOneMedico(agendamentoDto.getIdMedico());
        Paciente paciente = pacienteRepository.getOnePaciente(agendamentoDto.getIdPaciente());
        PlanoSaude planoSaude;
        try {
            planoSaude = planoSaudeRepository.getOnePlanoSaude(agendamentoDto.getIdPlanoSaude());
        }catch (NoObjectFoundException e) {
            planoSaude = null;
        }
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
}
