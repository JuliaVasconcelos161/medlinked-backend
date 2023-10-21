package com.medlinked.services.medicocrm_service;

import com.medlinked.entities.Especialidade;
import com.medlinked.entities.Medico;
import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.dtos.MedicoCrmResponseDto;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.exceptions.EspecialidadeException;
import com.medlinked.exceptions.ExistsException;
import com.medlinked.repositories.medicocrm_repository.MedicoCrmRepository;
import com.medlinked.repositories.planosaude_medico_repository.PlanoSaudeMedicoRepository;
import com.medlinked.services.especialidade_service.EspecialidadeService;
import com.medlinked.services.estado_service.EstadoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoCrmServiceImpl implements MedicoCrmService {

    private final EspecialidadeService especialidadeService;

    private final EstadoService estadoService;

    private final PlanoSaudeMedicoRepository planoSaudeMedicoRepository;
    private final MedicoCrmRepository medicoCrmRepository;

    public MedicoCrmServiceImpl(EspecialidadeService especialidadeService, EstadoService estadoService,
                                PlanoSaudeMedicoRepository planoSaudeMedicoRepository, MedicoCrmRepository medicoCrmRepository) {
        this.especialidadeService = especialidadeService;
        this.estadoService = estadoService;
        this.planoSaudeMedicoRepository = planoSaudeMedicoRepository;
        this.medicoCrmRepository = medicoCrmRepository;
    }

    @Override
    @Transactional
    public MedicoCRM createCrmMedico(Medico medico, MedicoDto medicoDto) {
        MedicoCRM medicoCrm = MedicoCRM
                .builder()
                .medico(medico)
                .numeroCrm(medicoDto.getNumeroCrm())
                .estado(estadoService.getOneEstado(medicoDto.getUfCrm()))
                .especialidades(especialidadeService.returnEspecialidadesByIds(medicoDto.getIdsEspecialidades()))
                .build();
        return medicoCrmRepository.saveCrm(medicoCrm);
    }

    @Override
    public void validateCrm(MedicoDto medicoDto, Integer idMedico) {
        if(medicoDto.getIdsEspecialidades().size() > 2)
            throw new EspecialidadeException();
        if(this.existsMedicoByNumeroCrm(medicoDto.getNumeroCrm(), idMedico))
            throw new ExistsException("Médico", "Número CRM");
    }
    private boolean existsMedicoByNumeroCrm(Integer numeroCrm, Integer idMedico) {
        return medicoCrmRepository.existsMedicoByNumeroCrm(numeroCrm, idMedico);
    }

    @Override
    @Transactional
    public MedicoCRM updateMedicoCrm(Medico medico, MedicoDto medicoDto) {
        MedicoCRM medicoCRM = medicoCrmRepository.getOneCrmByMedico(medico.getIdMedico());
        medicoCRM.setMedico(medico);
        medicoCRM.setNumeroCrm(medicoDto.getNumeroCrm());
        medicoCRM.setEstado(estadoService.getOneEstado(medicoDto.getUfCrm()));
        medicoCRM.setEspecialidades(especialidadeService.returnEspecialidadesByIds(medicoDto.getIdsEspecialidades()));
        return medicoCrmRepository.updateMedicoCrm(medicoCRM);
    }

    @Override
    public MedicoCrmResponseDto buildMedicoCrmResponseDto(Integer idMedico) {
        MedicoCrmResponseDto medicoCrmResponse = medicoCrmRepository.buildMedicoCrmResponseDto(idMedico);
        medicoCrmResponse.setEspecialidades(medicoCrmRepository.getEspecialidadesMedicoByCrm(idMedico));
        medicoCrmResponse
                .setPlanosSaudeMedico(planoSaudeMedicoRepository.getAllPlanosSaudeMedico(idMedico, null, null));
        return medicoCrmResponse;
    }

    @Override
    public List<Especialidade> getEspecialidadesMedicoByCrm(Integer idMedico) {
        return medicoCrmRepository.getEspecialidadesMedicoByCrm(idMedico);
    }
}
