package com.medlinked.services.usuario_service;

import com.medlinked.entities.dtos.UsuarioRegisterDto;
import com.medlinked.entities.dtos.UsuarioResponseDto;
import jakarta.transaction.Transactional;

public interface UsuarioService {
    @Transactional
    UsuarioResponseDto register(UsuarioRegisterDto usuarioRegisterDto, Integer idUsuario);
}
