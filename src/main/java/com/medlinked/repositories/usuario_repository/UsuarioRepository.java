package com.medlinked.repositories.usuario_repository;

import com.medlinked.entities.Usuario;

public interface UsuarioRepository {
    boolean existsByUsername(String username);

    void saveUsuario(Usuario usuario);

    Usuario returnUsuarioByUsername(String username);
}
