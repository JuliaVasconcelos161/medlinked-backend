package com.medlinked.services.planosaude_medico_service;

import com.medlinked.entities.Medico;
import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.dtos.MedicoPlanoSaudeDto;
import com.medlinked.repositories.medico_repository.MedicoRepository;
import com.medlinked.repositories.planosaude_medico_repository.PlanoSaudeMedicoRepository;
import com.medlinked.repositories.planosaude_repository.PlanoSaudeRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    @Override
    @Transactional
    public List<PlanoSaude> updateMedicoRemovePlanoSaude(Integer idMedico, Integer idPlanoSaude) {
        Medico medico = medicoRepository.getOneMedico(idMedico);
        PlanoSaude planoSaude = planoSaudeRepository.getOnePlanoSaude(idPlanoSaude);
        medico.removePlanoSaude(planoSaude);
        medicoRepository.saveMedico(medico);
        return medico.getPlanosSaude().stream().toList();
    }

    @Override
    public Page<PlanoSaude> getAllPlanosSaudeMedico(Integer idMedico, Integer page, Integer pageSize) {
        List<PlanoSaude> planosSaude = planoSaudeMedicoRepository.getAllPlanosSaudeMedico(idMedico, page, pageSize);
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Long total =  planoSaudeMedicoRepository.countPlanosSaudeMedico(idMedico);
        return new PageImpl<>(planosSaude, pageRequest, total);
    }

}
