package com.medlinked.repositories;

import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.Especialidade;
import jakarta.transaction.Transactional;

import java.util.List;

public interface MedicoCrmRepository {
    MedicoCRM saveCrm(MedicoCRM medicoCrm);

    MedicoCRM getOneCrmByMedico(Integer idMedico);

    List<Especialidade> getEspecialidadesMedicoByCrm(Integer idMedico);

    boolean existsMedicoByNumeroCrm(Integer numeroCrm);

    @Transactional
    MedicoCRM updateMedicoCrm(MedicoCRM medicoCrm);
}
