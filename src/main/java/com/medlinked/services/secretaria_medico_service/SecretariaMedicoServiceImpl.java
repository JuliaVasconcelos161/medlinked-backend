package com.medlinked.services.secretaria_medico_service;

import com.medlinked.entities.Medico;
import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.Secretaria;
import com.medlinked.repositories.medico_repository.MedicoRepository;
import com.medlinked.repositories.secretaria_medico_repository.SecretariaMedicoRepository;
import com.medlinked.repositories.secretaria_repository.SecretariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SecretariaMedicoServiceImpl implements SecretariaMedicoService {

    private final SecretariaMedicoRepository secretariaMedicoRepository;

    private final SecretariaRepository secretariaRepository;

    private final MedicoRepository medicoRepository;

    public SecretariaMedicoServiceImpl(SecretariaMedicoRepository secretariaMedicoRepository,
                                       SecretariaRepository secretariaRepository, MedicoRepository medicoRepository) {
        this.secretariaMedicoRepository = secretariaMedicoRepository;
        this.secretariaRepository = secretariaRepository;
        this.medicoRepository = medicoRepository;
    }

    @Transactional
    @Override
    public Set<Medico> associateSecretariaMedico(Integer idSecretaria, Integer idMedico) {
        Secretaria secretaria = secretariaRepository.getOneSecretaria(idSecretaria);
        Medico medico = medicoRepository.getOneMedico(idMedico);
        secretaria.getMedicos().add(medico);
        secretariaRepository.updateSecretaria(secretaria);
        return secretaria.getMedicos();
    }

    @Transactional
    @Override
    public Set<Medico> disassociateSecretariaMedico(Integer idSecretaria, Integer idMedico) {
        Secretaria secretaria = secretariaRepository.getOneSecretaria(idSecretaria);
        Medico medico = medicoRepository.getOneMedico(idMedico);
        secretaria.getMedicos().remove(medico);
        secretariaRepository.updateSecretaria(secretaria);
        return secretaria.getMedicos();
    }

    @Override
    public Page<MedicoCRM> getAllMedicosSecretaria(Integer idSecretaria, int page, int pageSize) {
        List<MedicoCRM> medicos = secretariaMedicoRepository.getAllMedicosSecretaria(idSecretaria, page, pageSize);
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Long total =  secretariaMedicoRepository.countMedicosSecretaria(idSecretaria);
        return new PageImpl<>(medicos, pageRequest, total);
    }
}
