package com.medlinked.repositories.medico_repository;

import com.medlinked.entities.Medico;
import com.medlinked.entities.PlanoSaude;

import java.util.List;

public interface MedicoRepository {

    Medico saveMedico(Medico medico);

    List<Medico> getAllMedicos();

    Medico getOneMedico(Integer idMedico);

    Medico updateMedico(Medico medico);
}
