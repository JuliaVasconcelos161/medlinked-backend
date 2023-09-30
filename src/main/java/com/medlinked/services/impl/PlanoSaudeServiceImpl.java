package com.medlinked.services.impl;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.PlanoSaudeDto;
import com.medlinked.exceptions.ExistsDescricao;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.repositories.PlanoSaudeRepository;
import com.medlinked.services.MedicoService;
import com.medlinked.services.PlanoSaudeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoSaudeServiceImpl implements PlanoSaudeService {

    private final PlanoSaudeRepository planoSaudeRepository;

    public PlanoSaudeServiceImpl(PlanoSaudeRepository planoSaudeRepository, MedicoService medicoService) {
        this.planoSaudeRepository = planoSaudeRepository;
    }

    @Override
    public List<PlanoSaude> getAllPlanosSaude() {
        return planoSaudeRepository.getAllPlanosSaude();
    }

    @Override
    @Transactional
    public PlanoSaude createPlanoSaude(PlanoSaudeDto planoSaudeDto) {
        if(planoSaudeRepository.existsPlanoSaudeByDescricao(planoSaudeDto.getDescricao()))
            throw new ExistsDescricao("Plano de Saúde");
        PlanoSaude planoSaude = PlanoSaude
                .builder()
                .descricao(planoSaudeDto.getDescricao())
                .build();
        return planoSaudeRepository.save(planoSaude);
    }

    @Override
    @Transactional
    public void deletePlanoSaude(Integer idPlanoSaude) {
        try{
            PlanoSaude planoSaude = planoSaudeRepository.getOnePlanoSaude(idPlanoSaude);
            planoSaudeRepository.delete(planoSaude);
        } catch (Exception e) {
            throw new MedLinkedException();
        }
    }

}
