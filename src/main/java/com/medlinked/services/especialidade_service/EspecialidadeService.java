package com.medlinked.services.especialidade_service;

import com.medlinked.entities.Especialidade;

import java.util.List;
import java.util.Set;

public interface EspecialidadeService {
    Set<Especialidade> returnEspecialidadesByIds(Set<Integer> idsEspecialidades);

    List<Especialidade> getAllEspecialidades();

    Especialidade getOneEspecialidade(Integer idEspecialidade);
}
