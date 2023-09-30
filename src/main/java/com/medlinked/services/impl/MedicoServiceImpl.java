package com.medlinked.services.impl;

import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.Medico;
import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.exceptions.ExistsCpfException;
import com.medlinked.repositories.MedicoRepository;
import com.medlinked.services.MedicoCrmService;
import com.medlinked.services.MedicoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    private final MedicoCrmService medicoCrmService;

    private final MedicoRepository medicoRepository;

    public MedicoServiceImpl(MedicoCrmService medicoCrmService, MedicoRepository medicoRepository) {
        this.medicoCrmService = medicoCrmService;
        this.medicoRepository = medicoRepository;
    }

    @Override
    @Transactional
    public MedicoCRM save(MedicoDto medicoDto) {
        if(this.existsMedicoByCpf(medicoDto.getCpf()))
            throw new ExistsCpfException("Medico");
        Medico medico = Medico.builder()
                .pessoa(
                        Pessoa.builder()
                                .nome(medicoDto.getNome())
                                .cpf(Long.parseLong(medicoDto.getCpf()))
                                .celular(medicoDto.getCelular())
                                .email(medicoDto.getEmail())
                                .build())
                .build();
        medico = medicoRepository.saveMedico(medico);
        medicoCrmService.createCrmMedico(medico,medicoDto);
        return medicoCrmService.getOneCrmByMedico(medico.getIdMedico());
    }

    @Override
    public List<Medico> getAll() {
        return medicoRepository.getAllMedicos();
    }

    private boolean existsMedicoByCpf(String cpf) {
        return medicoRepository.existsMedicoByCpf(cpf);
    }

}
