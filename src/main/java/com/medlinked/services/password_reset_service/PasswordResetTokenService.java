package com.medlinked.services.password_reset_service;

import com.medlinked.entities.Usuario;
import com.medlinked.entities.dtos.PasswordResetDto;
import jakarta.servlet.http.HttpServletRequest;

public interface PasswordResetTokenService {
    String resetPassword(HttpServletRequest request, String username);

    void changeUsuarioPassword(Usuario usuario, String senha);

    Usuario savePassword(PasswordResetDto passwordResetDto);

    void validatePasswordResetToken(String token);

}
