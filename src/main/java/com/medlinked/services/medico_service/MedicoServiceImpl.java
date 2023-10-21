package com.medlinked.services.medico_service;

import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.Medico;
import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.MedicoCrmResponseDto;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.repositories.medico_repository.MedicoRepository;
import com.medlinked.services.medicocrm_service.MedicoCrmService;
import com.medlinked.services.pessoa_service.PessoaService;
import com.medlinked.services.secretaria_medico_service.SecretariaMedicoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    private final SecretariaMedicoService secretariaMedicoService;

    private final MedicoCrmService medicoCrmService;

    private final PessoaService pessoaService;

    private final MedicoRepository medicoRepository;

    public MedicoServiceImpl(SecretariaMedicoService secretariaMedicoService, MedicoCrmService medicoCrmService,
                             PessoaService pessoaService, MedicoRepository medicoRepository) {
        this.secretariaMedicoService = secretariaMedicoService;
        this.medicoCrmService = medicoCrmService;
        this.pessoaService = pessoaService;
        this.medicoRepository = medicoRepository;
    }

    @Override
    @Transactional
    public MedicoCrmResponseDto createMedico(MedicoDto medicoDto, Integer idSecretaria) {
        pessoaService.validateNewEspecializacaoPessoa(medicoDto.getCpf(), medicoDto.getEmail(), "Medico");
        medicoCrmService.validateCrm(medicoDto, null);
        Pessoa pessoa = pessoaService.returnPessoaByCpf(medicoDto.getCpf());
        Medico medico = Medico.builder()
                .pessoa(pessoa == null ? pessoaService.createPessoa(medicoDto, "Medico") : pessoa)
                .build();
        medico = medicoRepository.saveMedico(medico);
        secretariaMedicoService.associateSecretariaMedico(idSecretaria, medico.getIdMedico());
        MedicoCRM medicoCrm = medicoCrmService.createCrmMedico(medico,medicoDto);
        return medicoCrmService.buildMedicoCrmResponseDto(medicoCrm.getIdMedico());
    }

    @Override
    public List<Medico> getAll() {
        return medicoRepository.getAllMedicos();
    }

    @Override
    @Transactional
    public MedicoCrmResponseDto updateMedico(Integer idMedico, MedicoDto medicoDto) {
        medicoCrmService.validateCrm(medicoDto, idMedico);
        Medico medico = medicoRepository.getOneMedico(idMedico);
        medico.setPessoa(pessoaService.updatePessoa(idMedico, medicoDto, "Medico"));
        medicoRepository.updateMedico(medico);
        MedicoCRM medicoCrm = medicoCrmService.updateMedicoCrm(medico, medicoDto);
        return medicoCrmService.buildMedicoCrmResponseDto(medicoCrm.getIdMedico());
    }

}
