package com.medlinked.services;

import com.medlinked.entities.Medico;
import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.dtos.MedicoDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface MedicoService {
    MedicoCRM createMedico(MedicoDto medicoDto);

    List<Medico> getAll();

    MedicoCRM updateMedico(Integer idMedico, MedicoDto medicoDto);
}
