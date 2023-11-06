package com.medlinked.repositories.medicocrm_repository;

import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.Especialidade;
import com.medlinked.entities.dtos.MedicoCrmResponseDto;

import java.util.List;

public interface MedicoCrmRepository {
    MedicoCRM saveCrm(MedicoCRM medicoCrm);

    MedicoCRM getOneCrmByMedico(Integer idMedico);

    List<Especialidade> getEspecialidadesMedicoByCrm(Integer idMedico);

    boolean existsMedicoByNumeroCrm(Integer numeroCrm, Integer idMedico);


    MedicoCRM updateMedicoCrm(MedicoCRM medicoCrm);

    MedicoCrmResponseDto buildMedicoCrmResponseDto(Integer idMedico);

    List<MedicoCrmResponseDto> buildMedicosCrmResponseByIdsMedicos(List<Integer> idsMedicos, Integer page, Integer pageSize);

    void deleteMedicoCrm(MedicoCRM medicoCrm);
}
