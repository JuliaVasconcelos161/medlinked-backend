package com.medlinked.repositories.planosaude_medico_repository;

import com.medlinked.entities.Medico;

import java.util.List;

public interface PlanoSaudeMedicoRepository {
    List<Medico> getAllMedicosPlanoSaude(Integer idPlanoSaude);
}
