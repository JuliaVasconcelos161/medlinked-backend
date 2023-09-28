package com.medlinked.services.impl;

import com.medlinked.entities.*;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.entities.dtos.MedicoResponseDto;
import com.medlinked.exceptions.ExistsCpf;
import com.medlinked.repositories.MedicoRepository;
import com.medlinked.services.CrmService;
import com.medlinked.services.MedicoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    public MedicoResponseDto save(MedicoDto medicoDto) {
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
        return this.getOneMedico(medico.getIdMedico());
    }

    @Override
    public List<Medico> getAll() {
        return medicoRepository.getAllMedicos();
    }

    @Override
    public MedicoResponseDto getOneMedico(Integer idMedico) {
        MedicoResponseDto medicoResponseDto;
        medicoResponseDto = medicoRepository.getOneMedico(idMedico);
        medicoResponseDto.setPlanosSaude(medicoRepository.getPlanosSaudeMedico(idMedico));
        CRM crm = crmService.getOneMedicoByCrm(idMedico);
        crm.setEspecialidades(new HashSet<>(crmService.getEspecialidadesMedicoByCrm(idMedico)));
        medicoResponseDto.setCrm(crm);
        return medicoResponseDto;
    }

    @Override
    public List<Medico> getAllMedicosPlanoSaude(Integer idPlanoSaude) {
        return medicoRepository.getAllMedicosPlanoSaude(idPlanoSaude);
    }

    @Override
    public void deleteMedicosPlanoSaude(List<Medico> medicos, PlanoSaude planoSaude) {
        medicos.forEach(medico -> medico.getPlanosSaude().remove(planoSaude));
    }


    private boolean existsMedicoByCpf(String cpf) {
        return medicoRepository.existsMedicoByCpf(cpf);
    }



}
