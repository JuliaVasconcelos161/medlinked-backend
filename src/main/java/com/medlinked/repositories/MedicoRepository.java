package com.medlinked.repositories;

import com.medlinked.entities.Medico;

import java.util.List;

public interface MedicoRepository {

    Medico saveMedico(Medico medico);

    List<Medico> getAllMedicos();

    Medico getOneMedico(Long idMedico);

    boolean existsMedicoByCpf(Long cpf);

    void deleteMedico(Long idMedico);
}
