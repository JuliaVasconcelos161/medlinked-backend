package com.medlinked.services.impl;

import com.medlinked.entities.CRM;
import com.medlinked.entities.Estado;
import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.repositories.CrmRepository;
import com.medlinked.services.CrmService;
import com.medlinked.services.EspecialidadeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
        CRM crm = CRM
                .builder()
                .medico(medico)
                .numeroCrm(medicoDto.getNumeroCrm())
                .estado(Estado.builder().uf(medicoDto.getUfCrm()).build())
                .especialidades(especialidadeService.createEspecialidades(medicoDto.getIdsEspecialidades()))
                .build();
        return  crmRepository.saveCrm(crm);
    }
}
