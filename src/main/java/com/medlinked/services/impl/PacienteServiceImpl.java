package com.medlinked.services.impl;

import com.medlinked.entities.Endereco;
import com.medlinked.entities.Paciente;
import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.PacienteDto;
import com.medlinked.entities.dtos.PacienteResponseDto;
import com.medlinked.repositories.PacienteRepository;
import com.medlinked.services.EnderecoService;
import com.medlinked.services.PacienteService;
import com.medlinked.services.PessoaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    private final PessoaService pessoaService;

    private final EnderecoService enderecoService;

    public PacienteServiceImpl(PacienteRepository pacienteRepository, PessoaService pessoaService, EnderecoService enderecoService) {
        this.pacienteRepository = pacienteRepository;
        this.pessoaService = pessoaService;
        this.enderecoService = enderecoService;
    }

    @Transactional
    @Override
    public PacienteResponseDto createPaciente(PacienteDto pacienteDto) {
        pessoaService.validateNewEspecializacaoPessoa(
                pacienteDto.getCpf(), pacienteDto.getEmail(), "Paciente");
        Pessoa pessoa = pessoaService.returnPessoaByCpf(pacienteDto.getCpf());
        Paciente paciente = Paciente.builder()
                .pessoa(pessoa == null ? pessoaService.createPessoa(pacienteDto, "Paciente") : pessoa)
                .build();
        paciente = pacienteRepository.savePaciente(paciente);
        Endereco endereco = enderecoService.createEndereco(pacienteDto.getEnderecoDto(), paciente);
        PacienteResponseDto pacienteResponseDto = PacienteResponseDto.builder()
                .paciente(paciente)
                .endereco(endereco)
                .build();
        return pacienteResponseDto;
    }
}
