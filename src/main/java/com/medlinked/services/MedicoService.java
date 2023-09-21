package com.medlinked.services;

import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoDto;

import java.util.List;

public interface MedicoService {
    Medico save(MedicoDto medicoDto);

    List<Medico> getAll();

    Medico getOneMedico(Long idMedico);

    boolean existsMedicoByCpf(String cpf);

//    void deleteMedico(Long idMedico);
}
