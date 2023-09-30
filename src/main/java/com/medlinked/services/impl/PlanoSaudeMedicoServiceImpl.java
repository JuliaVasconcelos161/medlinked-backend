package com.medlinked.services.impl;

import com.medlinked.entities.Medico;
import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.MedicoPlanoSaudeDto;
import com.medlinked.repositories.MedicoRepository;
import com.medlinked.repositories.PlanoSaudeMedicoRepository;
import com.medlinked.repositories.PlanoSaudeRepository;
import com.medlinked.services.PlanoSaudeMedicoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PlanoSaudeMedicoServiceImpl implements PlanoSaudeMedicoService {

    private final PlanoSaudeMedicoRepository planoSaudeMedicoRepository;

    private final PlanoSaudeRepository planoSaudeRepository;

    private final MedicoRepository medicoRepository;

    public PlanoSaudeMedicoServiceImpl(
            PlanoSaudeMedicoRepository planoSaudeMedicoRepository,
            PlanoSaudeRepository planoSaudeRepository,
            MedicoRepository medicoRepository) {
        this.planoSaudeMedicoRepository = planoSaudeMedicoRepository;
        this.planoSaudeRepository = planoSaudeRepository;
        this.medicoRepository = medicoRepository;
    }


    @Override
    @Transactional
    public List<PlanoSaude> updateMedicoPlanosSaude(Integer idMedico, MedicoPlanoSaudeDto medicoPlanoSaudeDto) {
        Medico medico = medicoRepository.getOneMedico(idMedico);
        Set<PlanoSaude> planosSaude = new HashSet<>();
        medicoPlanoSaudeDto.getIdsPlanosSaude().forEach(idPlano -> {
            PlanoSaude planoSaude = planoSaudeRepository.getOnePlanoSaude(idPlano);
            planosSaude.add(planoSaude);
            medico.getPlanosSaude().add(planoSaude);
            medicoRepository.saveMedico(medico);
            planoSaude.getMedicos().add(medico);
            planoSaudeRepository.save(planoSaude);
        });
        return planosSaude.stream().toList();
    }

}
