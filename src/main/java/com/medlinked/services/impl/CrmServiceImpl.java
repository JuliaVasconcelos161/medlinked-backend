package com.medlinked.services.impl;

import com.medlinked.entities.CRM;
import com.medlinked.entities.Especialidade;
import com.medlinked.entities.Estado;
import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.exceptions.EspecialidadeException;
import com.medlinked.repositories.CrmRepository;
import com.medlinked.services.CrmService;
import com.medlinked.services.EspecialidadeService;
import jakarta.transaction.Transactional;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmServiceImpl implements CrmService {

    private final EspecialidadeService especialidadeService;

    private final CrmRepository crmRepository;

    public CrmServiceImpl(EspecialidadeService especialidadeService, CrmRepository crmRepository) {
        this.especialidadeService = especialidadeService;
        this.crmRepository = crmRepository;
    }

    @Override
    @Transactional
    public CRM createCrmMedico(Medico medico, MedicoDto medicoDto) {
        if(BooleanUtils.isTrue(medicoDto.getIdsEspecialidades().size() > 2))
            throw new EspecialidadeException();
        CRM crm = CRM
                .builder()
                .medico(medico)
                .numeroCrm(medicoDto.getNumeroCrm())
                .estado(Estado.builder().uf(medicoDto.getUfCrm()).build())
                .especialidades(especialidadeService.createEspecialidades(medicoDto.getIdsEspecialidades()))
                .build();
        return  crmRepository.saveCrm(crm);
    }

    @Override
    public CRM getOneMedicoByCrm(Integer idMedico) {
        return crmRepository.getOneMedicoByCrm(idMedico);
    }

    @Override
    public List<Especialidade> getEspecialidadesMedicoByCrm(Integer idMedico) {
        return crmRepository.getEspecialidadesMedicoByCrm(idMedico);
    }
}
