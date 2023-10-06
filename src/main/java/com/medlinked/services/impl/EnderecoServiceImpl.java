package com.medlinked.services.impl;

import com.medlinked.entities.Endereco;
import com.medlinked.entities.Paciente;
import com.medlinked.entities.dtos.EnderecoDto;
import com.medlinked.repositories.EnderecoRepository;
import com.medlinked.services.EnderecoService;
import com.medlinked.services.EstadoService;
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
}
