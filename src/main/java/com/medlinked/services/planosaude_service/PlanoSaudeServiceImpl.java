package com.medlinked.services.planosaude_service;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.PlanoSaudeDto;
import com.medlinked.exceptions.ExistsDescricaoException;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.repositories.planosaude_paciente_repository.PlanoSaudePacienteRepository;
import com.medlinked.repositories.planosaude_repository.PlanoSaudeRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoSaudeServiceImpl implements PlanoSaudeService {

    private final PlanoSaudeRepository planoSaudeRepository;

    private final PlanoSaudePacienteRepository planoSaudePacienteRepository;

    public PlanoSaudeServiceImpl(PlanoSaudeRepository planoSaudeRepository,
                                 PlanoSaudePacienteRepository planoSaudePacienteRepository) {
        this.planoSaudeRepository = planoSaudeRepository;
        this.planoSaudePacienteRepository = planoSaudePacienteRepository;
    }

    @Override
    public List<PlanoSaude> getAllPlanosSaude() {
        return planoSaudeRepository.getAllPlanosSaude(null, null);
    }

    @Override
    @Transactional
    public PlanoSaude createPlanoSaude(PlanoSaudeDto planoSaudeDto) {
        if(planoSaudeRepository.existsPlanoSaudeByDescricao(planoSaudeDto.getDescricao()))
            throw new ExistsDescricaoException("Plano de Saúde");
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
            planoSaudePacienteRepository.disassociateAllPacientesPlanoSaude(idPlanoSaude);
            planoSaudeRepository.delete(planoSaude);
        } catch (Exception e) {
            throw new MedLinkedException();
        }
    }

    @Override
    public Page<PlanoSaude> getAllPlanosSaudePaginado(Integer page, Integer pageSize) {
        List<PlanoSaude> planosSaude = planoSaudeRepository.getAllPlanosSaude(page, pageSize);
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Long total =  planoSaudeRepository.countPlanosSaude();
        return new PageImpl<>(planosSaude, pageRequest, total);
    }

}
