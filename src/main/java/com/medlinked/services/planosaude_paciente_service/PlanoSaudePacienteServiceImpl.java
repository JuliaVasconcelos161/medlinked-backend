package com.medlinked.services.planosaude_paciente_service;

import com.medlinked.entities.Paciente;
import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.PlanoSaudePaciente;
import com.medlinked.entities.TipoPlanoSaude;
import com.medlinked.entities.dtos.PlanoSaudePacienteDto;
import com.medlinked.repositories.paciente_repository.PacienteRepository;
import com.medlinked.repositories.planosaude_paciente_repository.PlanoSaudePacienteRepository;
import com.medlinked.repositories.planosaude_repository.PlanoSaudeRepository;
import com.medlinked.services.tipoplanosaude_service.TipoPlanoSaudeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoSaudePacienteServiceImpl implements PlanoSaudePacienteService {

    private final TipoPlanoSaudeService tipoPlanoSaudeService;

    private final PlanoSaudePacienteRepository planoSaudePacienteRepository;

    private final PlanoSaudeRepository planoSaudeRepository;

    private final PacienteRepository pacienteRepository;

    public PlanoSaudePacienteServiceImpl(TipoPlanoSaudeService tipoPlanoSaudeService,
                                         PlanoSaudePacienteRepository planoSaudePacienteRepository,
                                         PlanoSaudeRepository planoSaudeRepository,
                                         PacienteRepository pacienteRepository) {
        this.tipoPlanoSaudeService = tipoPlanoSaudeService;
        this.planoSaudePacienteRepository = planoSaudePacienteRepository;
        this.planoSaudeRepository = planoSaudeRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    @Override
    public PlanoSaudePaciente associatePacientePlanoSaude(
            Integer idPaciente, Integer idPlanoSaude, PlanoSaudePacienteDto planoSaudePacienteDto) {
        PlanoSaude planoSaude = planoSaudeRepository.getOnePlanoSaude(idPlanoSaude);
        Paciente paciente = pacienteRepository.getOnePaciente(idPaciente);
        TipoPlanoSaude tipoPlanoSaude =
                tipoPlanoSaudeService.getOneTipoPlanoSaude(planoSaudePacienteDto.getIdTipoPlanoSaude());
        PlanoSaudePaciente.PlanoSaudePacientePk idPlanoSaudePaciente = PlanoSaudePaciente.PlanoSaudePacientePk.builder()
                        .planoSaude(planoSaude)
                        .paciente(paciente)
                        .build();
        PlanoSaudePaciente planoSaudePaciente = PlanoSaudePaciente.builder()
                .idPlanoSaudePaciente(idPlanoSaudePaciente)
                .tipoPlanoSaude(tipoPlanoSaude)
                .numeroCarteirinha(planoSaudePacienteDto.getNumeroCarteirinha())
                .build();
        return planoSaudePacienteRepository.savePlanoSaudePaciente(planoSaudePaciente);

    }
    @Transactional
    @Override
    public void disassociatePacientePlanoSaude(Integer idPaciente, Integer idPlanoSaude) {
        planoSaudePacienteRepository.disassociatePacientePlanoSaude(idPaciente, idPlanoSaude);
    }

    @Override
    public List<PlanoSaudePaciente> getAllPlanosSaudePaciente(Integer idPaciente) {
        return planoSaudePacienteRepository.getAllPlanosSaudePaciente(idPaciente);
    }

    @Transactional
    @Override
    public void disassociateAllPlanosSaudePaciente(Integer idPaciente) {
        planoSaudePacienteRepository.disassociateAllPlanosSaudePaciente(idPaciente);
    }
}
