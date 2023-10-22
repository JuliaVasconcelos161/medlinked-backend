package com.medlinked.repositories.secretaria_medico_repository;

import com.medlinked.entities.Secretaria;
import com.medlinked.entities.dtos.MedicoCrmResponseDto;

import java.util.List;

public interface SecretariaMedicoRepository {
    Long countMedicosSecretaria(Integer idSecretaria);

    List<MedicoCrmResponseDto> getAllMedicosSecretaria(Integer idSecretaria, int page, int pageSize);

    void disassociateMedicoAllSecretarias(Integer idMedico);

    List<Secretaria> getAllSecretariasMedico(Integer idMedico);
}
