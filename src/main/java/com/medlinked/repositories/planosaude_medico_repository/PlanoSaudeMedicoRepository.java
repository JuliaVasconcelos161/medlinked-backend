package com.medlinked.repositories.planosaude_medico_repository;

import com.medlinked.entities.Medico;
import com.medlinked.entities.PlanoSaude;

import java.util.List;

public interface PlanoSaudeMedicoRepository {
    List<Medico> getAllMedicosPlanoSaude(Integer idPlanoSaude);

    List<PlanoSaude> getAllPlanosSaudeMedico(Integer idMedico, int page, int pageSize);

    Long countPlanosSaudeMedico(Integer idMedico);
}
