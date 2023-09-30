package com.medlinked.services;

import com.medlinked.entities.Especialidade;

import java.util.List;
import java.util.Set;

public interface EspecialidadeService {
    Set<Especialidade> createEspecialidadesMedicoCrm(Set<Integer> idsEspecialidades);

    List<Especialidade> getAllEspecialidades();

    Especialidade getOneEspecialidade(Integer idEspecialidade);
}
