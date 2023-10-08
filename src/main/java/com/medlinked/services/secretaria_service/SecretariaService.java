package com.medlinked.services.secretaria_service;

import com.medlinked.entities.dtos.SecretariaDto;
import com.medlinked.entities.dtos.UsuarioResponseDto;

public interface SecretariaService {
    UsuarioResponseDto createSecretaria(SecretariaDto secretariaDto);
}
