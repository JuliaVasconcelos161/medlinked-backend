package com.medlinked.services.impl;

import com.medlinked.entities.Medico;
import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.repositories.MedicoRepositoryClass;
import com.medlinked.services.MedicoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepositoryClass medicoRepositoryClass;

    public MedicoServiceImpl(MedicoRepositoryClass medicoRepositoryClass) {
        this.medicoRepositoryClass = medicoRepositoryClass;
    }

    @Override
    @Transactional
    public Medico save(MedicoDto medicoDto) {
        Medico medico = Medico.builder()
                .pessoa(
                        Pessoa.builder()
                                .nome(medicoDto.getNome())
                                .cpf(medicoDto.getCpf())
                                .celular(medicoDto.getCelular())
                                .email(medicoDto.getEmail())
                                .build())
                .build();
        return medicoRepositoryClass.saveMedico(medico);
    }

    @Override
    public List<Medico> getAll() {
        return medicoRepositoryClass.getAllMedicos();
    }
}
