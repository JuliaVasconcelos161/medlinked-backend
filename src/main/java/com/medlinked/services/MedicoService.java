package com.medlinked.services;

import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoDto;

import java.util.List;

public interface MedicoService {
    MedicoCRM save(MedicoDto medicoDto);

    List<Medico> getAll();

}
