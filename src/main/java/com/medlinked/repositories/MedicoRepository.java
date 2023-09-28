package com.medlinked.repositories;

import com.medlinked.entities.Especialidade;
import com.medlinked.entities.Medico;
import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.MedicoResponseDto;

import java.util.List;

public interface MedicoRepository {

    Medico saveMedico(Medico medico);

    List<Medico> getAllMedicos();

    MedicoResponseDto getOneMedico(Integer idMedico);

    List<PlanoSaude> getPlanosSaudeMedico(Integer idMedico);


    boolean existsMedicoByCpf(String cpf);

    List<Medico> getAllMedicosPlanoSaude(Integer idPlanoSaude);

}
