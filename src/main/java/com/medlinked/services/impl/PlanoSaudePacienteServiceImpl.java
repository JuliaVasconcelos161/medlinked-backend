package com.medlinked.services.impl;

import com.medlinked.repositories.PlanoSaudePacienteRepository;
import com.medlinked.services.PlanoSaudePacienteService;
import org.springframework.stereotype.Service;

@Service
public class PlanoSaudePacienteServiceImpl implements PlanoSaudePacienteService {

    private final PlanoSaudePacienteRepository planoSaudePacienteRepository;

    public PlanoSaudePacienteServiceImpl(PlanoSaudePacienteRepository planoSaudePacienteRepository) {
        this.planoSaudePacienteRepository = planoSaudePacienteRepository;
    }


}
