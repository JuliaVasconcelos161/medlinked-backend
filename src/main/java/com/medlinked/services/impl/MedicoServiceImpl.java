package com.medlinked.services.impl;

import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.Medico;
import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.MedicoDto;
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
    public MedicoCRM createMedico(MedicoDto medicoDto) {
        pessoaService.validateNewEspecializacaoPessoa(medicoDto.getCpf(), medicoDto.getEmail(), "Medico");
        Pessoa pessoa = pessoaService.returnPessoaByCpf(medicoDto.getCpf());
        Medico medico = Medico.builder()
                .pessoa(pessoa == null ? pessoaService.createPessoa(medicoDto, "Medico") : pessoa)
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
        medico.setPessoa(pessoaService.updatePessoa(idMedico, medicoDto, "Medico"));
        medicoRepository.updateMedico(medico);
        return medicoCrmService.updateMedicoCrm(medico, medicoDto);
    }

}
