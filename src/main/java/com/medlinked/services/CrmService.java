package com.medlinked.services;

import com.medlinked.entities.CRM;
import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoDto;

public interface CrmService {

    CRM createCrmMedico(Medico medico, MedicoDto medicoDto);
}
