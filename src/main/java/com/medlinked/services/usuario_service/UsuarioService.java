package com.medlinked.services.usuario_service;

import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.UsuarioRegisterDto;
import com.medlinked.entities.dtos.UsuarioResponseDto;
import jakarta.transaction.Transactional;

public interface UsuarioService {

    UsuarioResponseDto register(UsuarioRegisterDto usuarioRegisterDto, Pessoa pessoa);

    UsuarioResponseDto authenticate(UsuarioRegisterDto usuarioRegisterDto);
}
