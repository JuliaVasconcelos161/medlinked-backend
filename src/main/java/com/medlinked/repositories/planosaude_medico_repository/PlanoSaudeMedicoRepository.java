package com.medlinked.repositories.planosaude_medico_repository;

import com.medlinked.entities.PlanoSaude;

import java.util.List;

public interface PlanoSaudeMedicoRepository {

    List<PlanoSaude> getAllPlanosSaudeMedico(Integer idMedico, Integer page, Integer pageSize);

    Long countPlanosSaudeMedico(Integer idMedico);
}
