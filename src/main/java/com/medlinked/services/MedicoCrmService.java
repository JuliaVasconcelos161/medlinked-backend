package com.medlinked.services;

import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.Especialidade;
import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoDto;

import java.util.List;

public interface MedicoCrmService {

    MedicoCRM createCrmMedico(Medico medico, MedicoDto medicoDto);

    MedicoCRM getOneCrmByMedico(Integer idMedico);

    List<Especialidade> getEspecialidadesMedicoByCrm(Integer idMedico);

    MedicoCRM updateMedicoCrm(Medico medico, MedicoDto medicoDto);
}
