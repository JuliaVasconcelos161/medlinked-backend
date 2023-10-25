package com.medlinked.services.password_reset_service;

import com.medlinked.entities.Usuario;
import com.medlinked.entities.dtos.PasswordResetDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.mail.SimpleMailMessage;

public interface PasswordResetTokenService {
    String resetPassword(HttpServletRequest request, String username);

    void changeUsuarioPassword(Usuario usuario, String senha);

    Usuario savePassword(PasswordResetDto passwordResetDto);

    String validatePasswordResetToken(String token);

    SimpleMailMessage constructResetTokenEmail(
            String contextPath, String token, Usuario usuario
    );

    SimpleMailMessage constructEmail(String subject, String body, Usuario usuario);
}
