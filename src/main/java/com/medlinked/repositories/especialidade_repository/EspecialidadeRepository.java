package com.medlinked.repositories.especialidade_repository;

import com.medlinked.entities.Especialidade;

import java.util.List;

public interface EspecialidadeRepository {
    List<Especialidade> getAllEspecialidades();

    Especialidade getOneEspecialidade(Integer idEspecialidade);
}
