package com.medlinked.services.agendamento_service;

import com.medlinked.entities.Agendamento;
import com.medlinked.entities.Medico;
import com.medlinked.entities.Paciente;
import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.AgendamentoDto;
import com.medlinked.enums.TipoAgendamento;
import com.medlinked.exceptions.AgendamentoException;
import com.medlinked.repositories.agendamento_repository.AgendamentoRepository;
import com.medlinked.repositories.medico_repository.MedicoRepository;
import com.medlinked.repositories.paciente_repository.PacienteRepository;
import com.medlinked.repositories.planosaude_repository.PlanoSaudeRepository;
import com.medlinked.services.email_service.EmailService;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.medlinked.utils.JavaDateFormatter.FORMATTER;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    private final EmailService emailService;

    private final MedicoRepository medicoRepository;

    private final PacienteRepository pacienteRepository;

    private final PlanoSaudeRepository planoSaudeRepository;

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoServiceImpl(EmailService emailService, MedicoRepository medicoRepository,
                                  PacienteRepository pacienteRepository, PlanoSaudeRepository planoSaudeRepository,
                                  AgendamentoRepository agendamentoRepository) {
        this.emailService = emailService;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.planoSaudeRepository = planoSaudeRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    @Transactional
    @Override
    public Agendamento createAgendamento(AgendamentoDto agendamentoDto) {
        this.validateHorarioAgendamento(agendamentoDto.getDataHoraInicioAgendamento(),
                agendamentoDto.getDataHoraFimAgendamento(), agendamentoDto.getIdMedico(), null);
        Medico medico = medicoRepository.getOneMedico(agendamentoDto.getIdMedico());
        Paciente paciente = agendamentoDto.getIdPaciente() != null ?
                pacienteRepository.getOnePaciente(agendamentoDto.getIdPaciente())
                : null;
        PlanoSaude planoSaude = agendamentoDto.getIdPlanoSaude() != null ?
                planoSaudeRepository.getOnePlanoSaude(agendamentoDto.getIdPlanoSaude())
                : null;
        Agendamento agendamento = Agendamento.builder()
                .tipoAgendamento(agendamentoDto.getTipoAgendamento())
                .dataHoraInicioAgendamento(LocalDateTime.parse(agendamentoDto.getDataHoraInicioAgendamento(), FORMATTER))
                .dataHoraFimAgendamento(LocalDateTime.parse(agendamentoDto.getDataHoraFimAgendamento(), FORMATTER))
                .descricao(agendamentoDto.getDescricao())
                .medico(medico)
                .paciente(paciente)
                .planoSaude(planoSaude)
                .build();
        agendamento = agendamentoRepository.saveAgendamento(agendamento);
        emailService.sendEmailAgendamentoConfirmacao(agendamento);
        return agendamento;
    }

    @Transactional
    @Override
    public Agendamento updateAgendamento(AgendamentoDto agendamentoDto, Integer idAgendamento) {
        this.validateHorarioAgendamento(agendamentoDto.getDataHoraInicioAgendamento(),
                agendamentoDto.getDataHoraFimAgendamento(), agendamentoDto.getIdMedico(), idAgendamento);
        Agendamento agendamento = agendamentoRepository.getOneAgendamento(idAgendamento);
        agendamento.setTipoAgendamento(agendamentoDto.getTipoAgendamento());
        agendamento.setDataHoraInicioAgendamento(LocalDateTime.parse(agendamentoDto.getDataHoraInicioAgendamento(), FORMATTER));
        agendamento.setDataHoraFimAgendamento(LocalDateTime.parse(agendamentoDto.getDataHoraFimAgendamento(), FORMATTER));
        agendamento.setDescricao(agendamentoDto.getDescricao());
        if(BooleanUtils.isFalse(agendamento.getMedico().getIdMedico().equals(agendamentoDto.getIdMedico()))) {
            Medico medico = medicoRepository.getOneMedico(agendamentoDto.getIdMedico());
            agendamento.setMedico(medico);
        }
        if(agendamento.getPaciente() == null ||
                BooleanUtils.isFalse(agendamento.getPaciente().getIdPaciente().equals(agendamentoDto.getIdPaciente()))) {
            Paciente paciente = agendamentoDto.getIdPaciente() != null ?
                    pacienteRepository.getOnePaciente(agendamentoDto.getIdPaciente())
                    : null;
            agendamento.setPaciente(paciente);
        }
        if(agendamento.getPlanoSaude() == null ||
                BooleanUtils.isFalse(agendamento.getPlanoSaude().getIdPlanoSaude().equals(agendamentoDto.getIdPlanoSaude()))) {
            PlanoSaude planoSaude = agendamentoDto.getIdPlanoSaude() != null ?
                    planoSaudeRepository.getOnePlanoSaude(agendamentoDto.getIdPlanoSaude())
                    : null;
            agendamento.setPlanoSaude(planoSaude);
        }
        this.enviaEmailCasoPacienteExistente(agendamento);
        return agendamentoRepository.updateAgendamento(agendamento);
    }

    @Override
    public Page<Agendamento> getAllAgendamentosMedicosSecretariaPaginado(Integer idSecretaria, Integer idMedico,
                                                                         Integer idPaciente, Integer mes, Integer ano,
                                                                         Integer dia, TipoAgendamento tipoAgendamento,
                                                                         Integer page, Integer pageSize) {
        List<Agendamento> agendamentos =  agendamentoRepository.getAllAgendamentosMedicosSecretaria(idSecretaria,
                idMedico, idPaciente, mes, ano, dia, tipoAgendamento, page, pageSize);
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Long total = agendamentoRepository.countGetAllAgendamentosMedicosSecretaria(idSecretaria, idMedico, idPaciente,
                mes, ano, dia, tipoAgendamento);
        return new PageImpl<>(agendamentos, pageRequest, total);
    }

    @Override
    public List<Agendamento> getAllAgendamentosMedicosSecretaria(Integer idSecretaria, Integer idMedico,
                                                                 Integer idPaciente, Integer mes, Integer ano,
                                                                 Integer dia, TipoAgendamento tipoAgendamento) {
        return agendamentoRepository.getAllAgendamentosMedicosSecretaria(idSecretaria,
                idMedico, idPaciente, mes, ano, dia, tipoAgendamento, null, null);
    }


    @Transactional
    @Override
    public void deleteAgendamento(Integer idAgendamento) {
        Agendamento agendamento = agendamentoRepository.getOneAgendamento(idAgendamento);
        agendamentoRepository.deleteAgendamento(agendamento);
    }
    @Transactional
    @Override
    public void deleteAllAgendamentosMedico(Integer idMedico) {
        agendamentoRepository.deleteAllAgendamentosMedico(idMedico);
    }
    @Override
    public void deleteAllAgendamentosPaciente(Integer idPaciente) {
        agendamentoRepository.deleteAllAgendamentosPaciente(idPaciente);
    }

    @Override
    public Agendamento getOneAgendamento(Integer idAgendamento) {
        return agendamentoRepository.getOneAgendamento(idAgendamento);
    }

    private void validateHorarioAgendamento(String dataHoraInicioAgendamento, String dataHoraFimAgendamento,
                                            Integer idMedico, Integer idAgendamento) {
        LocalDateTime inicio = LocalDateTime.parse(dataHoraInicioAgendamento, FORMATTER);
        LocalDateTime fim = LocalDateTime.parse(dataHoraFimAgendamento, FORMATTER);
        if(BooleanUtils.isTrue(this.isHorarioInicioDepoisHorarioFim(inicio, fim)))
            throw new AgendamentoException();
        this.validateHorarioAgendamentoExistente(inicio, fim, idMedico, idAgendamento);
    }

    public void validateHorarioAgendamentoExistente(LocalDateTime dataHorainicio, LocalDateTime dataHorafim,
                                                     Integer idMedico, Integer idAgendamento) {
        if(idAgendamento != null)
            agendamentoRepository
                    .validateHorarioAgendamentoExistente(
                            dataHorainicio.plusMinutes(1).toString(),dataHorafim.minusMinutes(1).toString(), idMedico, idAgendamento);
        else
            agendamentoRepository
                    .validateHorarioAgendamentoExistente(
                            dataHorainicio.plusMinutes(1).toString(),dataHorafim.minusMinutes(1).toString(), idMedico, null);
    }

    private boolean isHorarioInicioDepoisHorarioFim(LocalDateTime inicio, LocalDateTime fim) {
        return inicio.isAfter(fim);
    }

    private void enviaEmailCasoPacienteExistente(Agendamento agendamento) {
        if(agendamento.getPaciente() != null)
            emailService.sendEmailAgendamentoConfirmacao(agendamento);
    }
}
