package com.medlinked.services.impl;

import com.medlinked.entities.Especialidade;
import com.medlinked.repositories.EspecialidadeRepository;
import com.medlinked.services.EspecialidadeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EspecialidadeServiceImpl implements EspecialidadeService {

    private final EspecialidadeRepository especialidadeRepository;

    public EspecialidadeServiceImpl(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    @Override
    public Set<Especialidade> returnEspecialidadesByIds(Set<Integer> idsEspecialidades) {
        Set<Especialidade> especialidades = idsEspecialidades
                .stream()
                .map(this::getOneEspecialidade).collect(Collectors.toSet());
        return especialidades;
    }

    @Override
    public List<Especialidade> getAllEspecialidades() {
        return especialidadeRepository.getAllEspecialidades();
    }
    @Override
    public Especialidade getOneEspecialidade(Integer idEspecialidade) {
        return especialidadeRepository.getOneEspecialidade(idEspecialidade);
    }
}
