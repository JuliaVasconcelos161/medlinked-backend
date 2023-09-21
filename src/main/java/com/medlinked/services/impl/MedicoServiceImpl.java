package com.medlinked.services.impl;

import com.medlinked.entities.Medico;
import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.exceptions.ExistsCpf;
import com.medlinked.repositories.MedicoRepository;
import com.medlinked.repositories.MedicoRepositoryClass;
import com.medlinked.services.CrmService;
import com.medlinked.services.MedicoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    private final CrmService crmService;

    private final MedicoRepository medicoRepository;

    public MedicoServiceImpl(CrmService crmService, MedicoRepository medicoRepository) {
        this.crmService = crmService;
        this.medicoRepository = medicoRepository;
    }

    @Override
    @Transactional
    public Medico save(MedicoDto medicoDto) {
        if(this.existsMedicoByCpf(medicoDto.getCpf()))
            throw new ExistsCpf("Medico");
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
        crmService.createCrmMedico(medico,medicoDto);
        return medico;
    }

    @Override
    public List<Medico> getAll() {
        return medicoRepository.getAllMedicos();
    }

    @Override
    public Medico getOneMedico(Long idMedico) {
        return medicoRepository.getOneMedico(idMedico);
    }

    @Override
    public boolean existsMedicoByCpf(String cpf) {
        return medicoRepository.existsMedicoByCpf(cpf);
    }

//    @Override
//    @Transactional
//    public void deleteMedico(Long idMedico) {
//        medicoRepository.deleteMedico(idMedico);
//    }
}
