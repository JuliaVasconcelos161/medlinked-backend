package com.medlinked.repositories;

import com.medlinked.entities.Medico;
import com.medlinked.entities.PlanoSaude;

import java.util.List;

public interface MedicoRepository {

    Medico saveMedico(Medico medico);

    List<Medico> getAllMedicos();

    Medico getOneMedico(Integer idMedico);

    List<PlanoSaude> getPlanosSaudeMedico(Integer idMedico);


    boolean existsMedicoByCpf(String cpf);

    Medico updateMedico(Medico medico);
}
