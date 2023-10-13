package com.medlinked.services.medico_service;

import com.medlinked.entities.Medico;
import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.dtos.MedicoDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface MedicoService {
    MedicoCRM createMedico(MedicoDto medicoDto, Integer idSecretaria);

    List<Medico> getAll();

    MedicoCRM updateMedico(Integer idMedico, MedicoDto medicoDto);
}
