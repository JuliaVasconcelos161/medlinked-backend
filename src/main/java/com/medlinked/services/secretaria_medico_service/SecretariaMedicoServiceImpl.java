package com.medlinked.services.secretaria_medico_service;

import com.medlinked.entities.Medico;
import com.medlinked.entities.Secretaria;
import com.medlinked.entities.dtos.MedicoCrmResponseDto;
import com.medlinked.repositories.medico_repository.MedicoRepository;
import com.medlinked.repositories.planosaude_medico_repository.PlanoSaudeMedicoRepository;
import com.medlinked.repositories.secretaria_medico_repository.SecretariaMedicoRepository;
import com.medlinked.repositories.secretaria_repository.SecretariaRepository;
import com.medlinked.services.medicocrm_service.MedicoCrmService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretariaMedicoServiceImpl implements SecretariaMedicoService {

    private final MedicoCrmService medicoCrmService;

    private final PlanoSaudeMedicoRepository planoSaudeMedicoRepository;

    private final SecretariaMedicoRepository secretariaMedicoRepository;

    private final SecretariaRepository secretariaRepository;

    private final MedicoRepository medicoRepository;

    public SecretariaMedicoServiceImpl(MedicoCrmService medicoCrmService, PlanoSaudeMedicoRepository planoSaudeMedicoRepository,
                                       SecretariaMedicoRepository secretariaMedicoRepository,
                                       SecretariaRepository secretariaRepository, MedicoRepository medicoRepository) {
        this.medicoCrmService = medicoCrmService;
        this.planoSaudeMedicoRepository = planoSaudeMedicoRepository;
        this.secretariaMedicoRepository = secretariaMedicoRepository;
        this.secretariaRepository = secretariaRepository;
        this.medicoRepository = medicoRepository;
    }

    @Transactional
    @Override
    public void associateSecretariaMedico(Integer idSecretaria, Integer idMedico) {
        Secretaria secretaria = secretariaRepository.getOneSecretaria(idSecretaria);
        Medico medico = medicoRepository.getOneMedico(idMedico);
        secretaria.getMedicos().add(medico);
        secretariaRepository.updateSecretaria(secretaria);
    }

    @Transactional
    @Override
    public void disassociateSecretariaMedico(Integer idSecretaria, Integer idMedico) {
        Secretaria secretaria = secretariaRepository.getOneSecretaria(idSecretaria);
        Medico medico = medicoRepository.getOneMedico(idMedico);
        secretaria.getMedicos().remove(medico);
        secretariaRepository.updateSecretaria(secretaria);
    }

    @Override
    public Page<MedicoCrmResponseDto> getAllMedicosSecretaria(Integer idSecretaria, int page, int pageSize) {
        List<MedicoCrmResponseDto> medicos = secretariaMedicoRepository
                .getAllMedicosSecretaria(idSecretaria, page, pageSize);
        medicos.forEach(medico ->{
                    medico.setEspecialidades(medicoCrmService.getEspecialidadesMedicoByCrm(medico.getIdMedico()));
                    medico.setPlanosSaudeMedico(planoSaudeMedicoRepository
                            .getAllPlanosSaudeMedico(medico.getIdMedico(), null, null));
                });
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Long total =  secretariaMedicoRepository.countMedicosSecretaria(idSecretaria);
        return new PageImpl<>(medicos, pageRequest, total);
    }

    @Transactional
    @Override
    public void disassociateMedicoAllSecretarias(Medico medico) {
        List<Secretaria> secretariasMedico = secretariaMedicoRepository.getAllSecretariasMedico(medico.getIdMedico());
        secretariasMedico.forEach(secretaria -> {
            secretaria.getMedicos().remove(medico);
            secretariaRepository.updateSecretaria(secretaria);
        });
    }
}
