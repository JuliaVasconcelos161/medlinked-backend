package com.medlinked.services.impl;

import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.Especialidade;
import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.exceptions.EspecialidadeException;
import com.medlinked.exceptions.ExistsException;
import com.medlinked.repositories.MedicoCrmRepository;
import com.medlinked.services.EstadoService;
import com.medlinked.services.MedicoCrmService;
import com.medlinked.services.EspecialidadeService;
import jakarta.transaction.Transactional;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoCrmServiceImpl implements MedicoCrmService {

    private final EspecialidadeService especialidadeService;

    private final EstadoService estadoService;

    private final MedicoCrmRepository medicoCrmRepository;

    public MedicoCrmServiceImpl(EspecialidadeService especialidadeService, EstadoService estadoService, MedicoCrmRepository medicoCrmRepository) {
        this.especialidadeService = especialidadeService;
        this.estadoService = estadoService;
        this.medicoCrmRepository = medicoCrmRepository;
    }

    @Override
    @Transactional
    public MedicoCRM createCrmMedico(Medico medico, MedicoDto medicoDto) {
        this.validateCrm(medicoDto);
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
    public MedicoCRM getOneCrmByMedico(Integer idMedico) {
        return medicoCrmRepository.getOneCrmByMedico(idMedico);
    }

    @Override
    public List<Especialidade> getEspecialidadesMedicoByCrm(Integer idMedico) {
        return medicoCrmRepository.getEspecialidadesMedicoByCrm(idMedico);
    }

    private void validateCrm(MedicoDto medicoDto) {
        if(BooleanUtils.isTrue(medicoDto.getIdsEspecialidades().size() > 2))
            throw new EspecialidadeException();
        if(this.existsMedicoByNumeroCrm(medicoDto.getNumeroCrm()))
            throw new ExistsException("Médico", "Número CRM");
    }
    private boolean existsMedicoByNumeroCrm(Integer idMedico) {
        return medicoCrmRepository.existsMedicoByNumeroCrm(idMedico);
    }

    @Override
    @Transactional
    public MedicoCRM updateMedicoCrm(Medico medico, MedicoDto medicoDto) {
        this.validateCrm(medicoDto);
        MedicoCRM medicoCRM = medicoCrmRepository.getOneCrmByMedico(medico.getIdMedico());
        medicoCRM.setMedico(medico);
        medicoCRM.setNumeroCrm(medicoDto.getNumeroCrm());
        medicoCRM.setEstado(estadoService.getOneEstado(medicoDto.getUfCrm()));
        medicoCRM.setEspecialidades(especialidadeService.returnEspecialidadesByIds(medicoDto.getIdsEspecialidades()));
        return medicoCrmRepository.updateMedicoCrm(medicoCRM);
    }
}
