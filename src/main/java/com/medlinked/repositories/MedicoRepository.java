package com.medlinked.repositories;

import com.medlinked.entities.Especialidade;
import com.medlinked.entities.Medico;
import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.MedicoResponseDto;

import java.util.List;

public interface MedicoRepository {

    Medico saveMedico(Medico medico);

    List<Medico> getAllMedicos();

    MedicoResponseDto getOneMedicoByCrm(Integer idMedico);

    List<PlanoSaude> getPlanosSaudeMedicoByCrm(Integer idMedico);

    List<Especialidade> getEspecialidadesMedicoByCrm(Integer idMedico);

    boolean existsMedicoByCpf(String cpf);

//    void deleteMedico(Long idMedico);
}
