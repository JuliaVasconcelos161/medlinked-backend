package com.medlinked.services;

import com.medlinked.entities.Medico;
import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.MedicoDto;
import com.medlinked.entities.dtos.MedicoResponseDto;

import java.util.List;

public interface MedicoService {
    Medico save(MedicoDto medicoDto);

    List<Medico> getAll();

    MedicoResponseDto getOneMedico(Integer idMedico);

    boolean existsMedicoByCpf(String cpf);

//    void deleteMedico(Long idMedico);
}
