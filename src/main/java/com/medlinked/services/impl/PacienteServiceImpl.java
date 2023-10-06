package com.medlinked.services.impl;

import com.medlinked.entities.Endereco;
import com.medlinked.entities.Paciente;
import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.PacienteDto;
import com.medlinked.repositories.PacienteRepository;
import com.medlinked.services.EstadoService;
import com.medlinked.services.PacienteService;
import com.medlinked.services.PessoaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    private final PessoaService pessoaService;

    private final EstadoService estadoService;

    public PacienteServiceImpl(PacienteRepository pacienteRepository, PessoaService pessoaService, EstadoService estadoService) {
        this.pacienteRepository = pacienteRepository;
        this.pessoaService = pessoaService;
        this.estadoService = estadoService;
    }

    @Transactional
    @Override
    public Paciente createPaciente(PacienteDto pacienteDto) {
        pessoaService.validateNewEspecializacaoPessoa(
                pacienteDto.getCpf(), pacienteDto.getEmail(), "Paciente");
        Pessoa pessoa = pessoaService.returnPessoaByCpf(pacienteDto.getCpf());
        Paciente paciente = Paciente.builder()
                .pessoa(pessoa == null ? pessoaService.createPessoa(pacienteDto, "Paciente") : pessoa)
                .build();
        return pacienteRepository.savePaciente(paciente);
    }
}
