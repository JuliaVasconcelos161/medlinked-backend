package com.medlinked.services.secretaria_service;

import com.medlinked.entities.Secretaria;
import com.medlinked.entities.dtos.SecretariaUsuarioDto;
import com.medlinked.entities.dtos.SecretariaDto;
import com.medlinked.entities.dtos.UsuarioResponseDto;

public interface SecretariaService {
    UsuarioResponseDto createSecretaria(SecretariaUsuarioDto secretariaUsuarioDto);
    Secretaria updateSecretaria(SecretariaDto secretariaDto, Integer idSecretaria);

    Secretaria getOneSecretaria(Integer idSecretaria);

    void deleteSecretaria(Integer idSecretaria);
}
