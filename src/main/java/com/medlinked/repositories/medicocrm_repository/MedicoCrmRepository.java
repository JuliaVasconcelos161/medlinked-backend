package com.medlinked.repositories.medicocrm_repository;

import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.Especialidade;
import com.medlinked.entities.dtos.MedicoCrmResponseDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface MedicoCrmRepository {
    MedicoCRM saveCrm(MedicoCRM medicoCrm);

    MedicoCRM getOneCrmByMedico(Integer idMedico);

    List<Especialidade> getEspecialidadesMedicoByCrm(Integer idMedico);

    boolean existsMedicoByNumeroCrm(Integer numeroCrm, Integer idMedico);


    MedicoCRM updateMedicoCrm(MedicoCRM medicoCrm);

    MedicoCrmResponseDto getOneMedicoCrmResponseDto(Integer idMedico);
}
