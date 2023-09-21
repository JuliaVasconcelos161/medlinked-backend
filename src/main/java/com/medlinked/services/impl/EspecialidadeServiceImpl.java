package com.medlinked.services.impl;

import com.medlinked.entities.Especialidade;
import com.medlinked.services.EspecialidadeService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EspecialidadeServiceImpl implements EspecialidadeService {
    @Override
    public Set<Especialidade> createEspecialidades(Set<Integer> idsEspecialidades) {
        Set<Especialidade> especialidades = idsEspecialidades
                .stream()
                .map(id -> Especialidade
                        .builder()
                        .idEspecialidade(id)
                        .build())
                .collect(Collectors.toSet());
        return especialidades;
    }
}
