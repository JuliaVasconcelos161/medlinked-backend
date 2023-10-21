package com.medlinked.services.secretaria_medico_service;

import com.medlinked.entities.Medico;
import com.medlinked.entities.MedicoCRM;
import com.medlinked.entities.dtos.MedicoCrmResponseDto;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface SecretariaMedicoService {
    Set<Medico> associateSecretariaMedico(Integer idSecretaria, Integer idMedico);

    Set<Medico> disassociateSecretariaMedico(Integer idSecretaria, Integer idMedico);

    Page<MedicoCrmResponseDto> getAllMedicosSecretaria(Integer idSecretaria, int page, int pageSize);
}
