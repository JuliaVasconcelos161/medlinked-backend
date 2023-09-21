package com.medlinked.services;

import com.medlinked.entities.Especialidade;

import java.util.Set;

public interface EspecialidadeService {
    Set<Especialidade> createEspecialidades(Set<Integer> idsEspecialidades);
}
