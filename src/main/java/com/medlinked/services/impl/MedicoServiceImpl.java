package com.medlinked.services.impl;

import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.Medico;
import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.exceptions.ExistsCpfException;
import com.medlinked.repositories.MedicoRepository;
import com.medlinked.services.MedicoCrmService;
import com.medlinked.services.MedicoService;
import com.medlinked.services.PessoaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    private final MedicoCrmService medicoCrmService;

    private final PessoaService pessoaService;

    private final MedicoRepository medicoRepository;

    public MedicoServiceImpl(MedicoCrmService medicoCrmService, PessoaService pessoaService, MedicoRepository medicoRepository) {
        this.medicoCrmService = medicoCrmService;
        this.pessoaService = pessoaService;
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

    @Override
    @Transactional
    public MedicoCRM updateMedico(Integer idMedico, MedicoDto medicoDto) {
        Medico medico = medicoRepository.getOneMedico(idMedico);
        boolean isMedicoCpfEqualsMedicoDtoCpf = medico.getPessoa().getCpf().equals(Long.parseLong(medicoDto.getCpf()));
        if(this.existsMedicoByCpf(medicoDto.getCpf()) && !isMedicoCpfEqualsMedicoDtoCpf)
            throw new ExistsCpfException("Medico");
        medico.setPessoa(pessoaService.updatePessoa(idMedico, medicoDto));
        medicoRepository.updateMedico(medico);
        return medicoCrmService.updateMedicoCrm(medico, medicoDto);
    }

    private boolean existsMedicoByCpf(String cpf) {
        return medicoRepository.existsMedicoByCpf(cpf);
    }

}
