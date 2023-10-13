package com.medlinked.repositories.secretaria_medico_repository;

import com.medlinked.entities.MedicoCRM;

import java.util.List;

public interface SecretariaMedicoRepository {
    Long countMedicosSecretaria(Integer idSecretaria);

    List<MedicoCRM> getAllMedicosSecretaria(Integer idSecretaria, int page, int pageSize);
}
