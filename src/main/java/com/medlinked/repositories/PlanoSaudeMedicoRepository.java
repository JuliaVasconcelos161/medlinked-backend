package com.medlinked.repositories;

import com.medlinked.entities.Medico;

import java.util.List;

public interface PlanoSaudeMedicoRepository {
    List<Medico> getAllMedicosPlanoSaude(Integer idPlanoSaude);
}
