package com.medlinked.services.paciente_service;

import com.medlinked.entities.Endereco;
import com.medlinked.entities.Paciente;
import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.PacienteDto;
import com.medlinked.entities.dtos.PacienteResponseDto;
import com.medlinked.repositories.paciente_repository.PacienteRepository;
import com.medlinked.services.agendamento_service.AgendamentoService;
import com.medlinked.services.endereco_service.EnderecoService;
import com.medlinked.services.pessoa_service.PessoaService;
import com.medlinked.services.planosaude_paciente_service.PlanoSaudePacienteService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PlanoSaudePacienteService planoSaudePacienteService;

    private final AgendamentoService agendamentoService;

    private final PessoaService pessoaService;

    private final EnderecoService enderecoService;

    private final PacienteRepository pacienteRepository;
    public PacienteServiceImpl(PlanoSaudePacienteService planoSaudePacienteService, AgendamentoService agendamentoService,
                               PacienteRepository pacienteRepository, PessoaService pessoaService,
                               EnderecoService enderecoService) {
        this.planoSaudePacienteService = planoSaudePacienteService;
        this.agendamentoService = agendamentoService;
        this.pacienteRepository = pacienteRepository;
        this.pessoaService = pessoaService;
        this.enderecoService = enderecoService;
    }

    @Transactional
    @Override
    public PacienteResponseDto createPaciente(PacienteDto pacienteDto) {
        pessoaService.validateNewPessoa(
                pacienteDto.getCpf(), pacienteDto.getEmail(), "Paciente");
        Pessoa pessoa = pessoaService.returnPessoaByCpf(pacienteDto.getCpf());
        Paciente paciente = Paciente.builder()
                .pessoa(pessoa == null ? pessoaService.createPessoa(pacienteDto, "Paciente") : pessoa)
                .build();
        paciente = pacienteRepository.savePaciente(paciente);
        Endereco endereco = enderecoService.createEndereco(pacienteDto.getEnderecoDto(), paciente);
        return new PacienteResponseDto(paciente, endereco);
    }

    @Override
    public PacienteResponseDto getOnePaciente(Integer idPaciente) {
        Paciente paciente = pacienteRepository.getOnePaciente(idPaciente);
        Endereco endereco = enderecoService.getOneEndereco(idPaciente);
        return new PacienteResponseDto(paciente, endereco);
    }

    @Transactional
    @Override
    public PacienteResponseDto updatePaciente(Integer idPaciente, PacienteDto pacienteDto) {
        Paciente paciente = pacienteRepository.getOnePaciente(idPaciente);
        paciente.setPessoa(pessoaService.updatePessoa(idPaciente, pacienteDto, "Paciente"));
        paciente = pacienteRepository.updatePaciente(paciente);
        Endereco endereco = enderecoService.updateEndereco(pacienteDto.getEnderecoDto(), paciente);
        return new PacienteResponseDto(paciente, endereco);
    }

    @Override
    public Page<Paciente> getAllPacientesPaginado(String nomePaciente, String cpf, Integer page, Integer pageSize) {
        List<Paciente> pacientes = pacienteRepository.getAllPacientes(nomePaciente, cpf, page, pageSize);
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Long total =  pacienteRepository.countPacientes();
        return new PageImpl<>(pacientes, pageRequest, total);
    }

    @Override
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.getAllPacientes(null, null, null, null);
    }

    @Transactional
    @Override
    public void deletePaciente(Integer idPaciente) {
        Paciente paciente = pacienteRepository.getOnePaciente(idPaciente);
        agendamentoService.deleteAllAgendamentosPaciente(idPaciente);
        planoSaudePacienteService.disassociateAllPlanosSaudePaciente(idPaciente);
        enderecoService.deleteEndereco(idPaciente);
        pacienteRepository.deletePaciente(paciente);
        if(pessoaService.existsPessoa(idPaciente))
            pessoaService.deletePessoa(idPaciente);
    }
}
