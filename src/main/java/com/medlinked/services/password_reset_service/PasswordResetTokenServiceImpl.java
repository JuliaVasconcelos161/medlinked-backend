package com.medlinked.services.password_reset_service;

import com.medlinked.entities.PasswordResetToken;
import com.medlinked.entities.Usuario;
import com.medlinked.entities.dtos.PasswordResetDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.repositories.password_reset_repository.PasswordResetTokenRepository;
import com.medlinked.repositories.usuario_repository.UsuarioRepository;
import com.medlinked.services.email_service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    private final EmailService emailService;

    private final UsuarioRepository usuarioRepository;

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final PasswordEncoder passwordEncoder;

    public PasswordResetTokenServiceImpl(EmailService emailService, UsuarioRepository usuarioRepository,
                                         PasswordResetTokenRepository passwordResetTokenRepository,
                                         PasswordEncoder passwordEncoder) {
        this.emailService = emailService;

        this.usuarioRepository = usuarioRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public String resetPassword(HttpServletRequest request, String username) {
        Usuario usuario = usuarioRepository.returnUsuarioByUsername(username);
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = passwordResetTokenRepository
                .returnPasswordResetTokenByUsuario(usuario.getUsername());
        if(passwordResetToken == null)
            this.createPasswordResetTokenForUsuario(usuario, token);
        else
            this.updatePasswordResetTokenForUsuario(passwordResetToken, token);
        emailService.sendEmailResetPassword(usuario);
        return token;
    }


    @Override
    public void changeUsuarioPassword(Usuario usuario, String senha) {
        usuario.setPassword(senha);
        usuarioRepository.saveUsuario(usuario);
    }

    @Transactional
    @Override
    public Usuario savePassword(PasswordResetDto passwordResetDto) {
        this.validatePasswordResetToken(passwordResetDto.getToken());
        PasswordResetToken passwordResetToken = passwordResetTokenRepository
                .getPasswordResetTokenByToken(passwordResetDto.getToken());
        Usuario usuario = passwordResetToken.getUsuario();
        this.changeUsuarioPassword(usuario, passwordEncoder.encode(passwordResetDto.getNewPassword()));
        return usuario;

    }

    @Override
    public void validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordResetTokenRepository.getPasswordResetTokenByToken(token);
        String response = BooleanUtils.isFalse(isTokenFound(passToken)) ? "Token inválido"
                : BooleanUtils.isTrue(isTokenExpired(passToken)) ? "Token expirado"
                : null;
        if(response != null)
            throw new MedLinkedException(response, HttpStatus.BAD_REQUEST);
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        return passToken.getExpiryDate().after(new Date());
    }

    private void createPasswordResetTokenForUsuario(Usuario usuario, String token) {
        passwordResetTokenRepository.savePasswordResetToken(new PasswordResetToken(usuario, token));
    }

    private void updatePasswordResetTokenForUsuario(PasswordResetToken passwordResetToken, String token) {
        passwordResetToken.setToken(token);
        passwordResetToken.setExpiryDate(new Date());
        passwordResetTokenRepository.updatePasswordResetToken(passwordResetToken);
    }
}
