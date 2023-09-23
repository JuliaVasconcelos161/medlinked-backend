package com.medlinked.repositories;

import com.medlinked.entities.CRM;
import com.medlinked.entities.Especialidade;

import java.util.List;

public interface CrmRepository {
    CRM saveCrm(CRM crm);

    CRM getOneMedicoByCrm(Integer idMedico);

    List<Especialidade> getEspecialidadesMedicoByCrm(Integer idMedico);
}
