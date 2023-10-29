package com.medlinked.services.usuario_service;

import com.medlinked.entities.Pessoa;
import com.medlinked.entities.Usuario;
import com.medlinked.entities.dtos.UpdateSenhaUsuarioDto;
import com.medlinked.entities.dtos.UsuarioRegisterDto;
import com.medlinked.entities.dtos.UsuarioResponseDto;

public interface UsuarioService {

    UsuarioResponseDto register(UsuarioRegisterDto usuarioRegisterDto, Pessoa pessoa);

    UsuarioResponseDto authenticate(UsuarioRegisterDto usuarioRegisterDto);

    Usuario updateSenhaUsuario(UpdateSenhaUsuarioDto updateSenhaUsuarioDto, Integer idUsuario);
    void deleteUsuario(Integer idUsuario);
}
