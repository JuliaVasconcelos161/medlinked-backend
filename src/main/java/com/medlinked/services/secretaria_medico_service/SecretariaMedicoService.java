package com.medlinked.services.secretaria_medico_service;

import com.medlinked.entities.Medico;
import com.medlinked.entities.dtos.MedicoCrmResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SecretariaMedicoService {
    void associateSecretariaMedico(Integer idSecretaria, Integer idMedico);

    void disassociateSecretariaMedico(Integer idSecretaria, Integer idMedico);

    Page<MedicoCrmResponseDto> getAllMedicosSecretariaPaginado(Integer idSecretaria, int page, int pageSize);

    List<MedicoCrmResponseDto> getAllMedicosSecretaria(Integer idSecretaria);

    void disassociateMedicoAllSecretarias(Medico medico);
}
