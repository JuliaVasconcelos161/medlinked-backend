package com.medlinked.repositories.secretaria_repository;

import com.medlinked.entities.Secretaria;

public interface SecretariaRepository {
    Secretaria saveSecretaria(Secretaria secretaria);

    Secretaria getOneSecretaria(Integer idSecretaria);

    Secretaria updateSecretaria(Secretaria secretaria);

    void deleteSecretaria(Secretaria secretaria);
}
