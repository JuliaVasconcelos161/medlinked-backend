package com.medlinked.services.endereco_service;

import com.medlinked.entities.Endereco;
import com.medlinked.entities.Paciente;
import com.medlinked.entities.dtos.EnderecoDto;
import com.medlinked.repositories.endereco_repository.EnderecoRepository;
import com.medlinked.services.estado_service.EstadoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final EstadoService estadoService;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository, EstadoService estadoService) {
        this.enderecoRepository = enderecoRepository;
        this.estadoService = estadoService;
    }

    @Transactional
    @Override
    public Endereco createEndereco(EnderecoDto enderecoDto, Paciente paciente) {
        Endereco endereco = Endereco.builder()
                .cep(Long.parseLong(enderecoDto.getCep()))
                .logradouro(enderecoDto.getLogradouro())
                .numero(enderecoDto.getNumero())
                .complemento(enderecoDto.getComplemento())
                .bairro(enderecoDto.getBairro())
                .cidade(enderecoDto.getCidade())
                .estado(estadoService.getOneEstado(enderecoDto.getUfEstado()))
                .paciente(paciente)
                .build();
        return enderecoRepository.saveEndereco(endereco);
    }

    @Override
    public Endereco getOneEndereco(Integer idPaciente) {
        return enderecoRepository.getOneEndereco(idPaciente);
    }

    @Transactional
    @Override
    public Endereco updateEndereco(EnderecoDto enderecoDto, Paciente paciente) {
        Endereco endereco = enderecoRepository.getOneEndereco(paciente.getIdPaciente());
        endereco.setCep(Long.parseLong(enderecoDto.getCep()));
        endereco.setLogradouro(enderecoDto.getLogradouro());
        endereco.setBairro(enderecoDto.getBairro());
        endereco.setCidade(enderecoDto.getCidade());
        endereco.setEstado(estadoService.getOneEstado(enderecoDto.getUfEstado()));
        endereco.setComplemento(enderecoDto.getComplemento());
        endereco.setNumero(enderecoDto.getNumero());
        endereco.setPaciente(paciente);
        return enderecoRepository.updateEndereco(endereco);
    }
}
