package com.medlinked.services;

import com.medlinked.entities.CRM;
import com.medlinked.entities.Especialidade;
import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoDto;

import java.util.List;

public interface CrmService {

    CRM createCrmMedico(Medico medico, MedicoDto medicoDto);

    CRM getOneMedicoByCrm(Integer idMedico);

    List<Especialidade> getEspecialidadesMedicoByCrm(Integer idMedico);
}
