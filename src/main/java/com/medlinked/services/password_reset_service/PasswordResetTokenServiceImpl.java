package com.medlinked.services.password_reset_service;

import com.medlinked.entities.PasswordResetToken;
import com.medlinked.entities.Usuario;
import com.medlinked.entities.dtos.PasswordResetDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.repositories.password_reset_repository.PasswordResetTokenRepository;
import com.medlinked.repositories.usuario_repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final JavaMailSender javaMailSender;

    private final PasswordEncoder passwordEncoder;

    public PasswordResetTokenServiceImpl(UsuarioRepository usuarioRepository,
                                         PasswordResetTokenRepository passwordResetTokenRepository,
                                         JavaMailSender javaMailSender, PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.javaMailSender = javaMailSender;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public String resetPassword(HttpServletRequest request, String username) {
        Usuario usuario = usuarioRepository.returnUsuarioByUsername(username);
        String token = UUID.randomUUID().toString();
        this.createPasswordResetTokenForUsuario(usuario, token);
        javaMailSender.send(constructResetTokenEmail(request.getContextPath(), token, usuario));
        return token;
    }


    private void createPasswordResetTokenForUsuario(Usuario usuario, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(usuario, token);
        passwordResetTokenRepository.savePasswordResetToken(passwordResetToken);
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
    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordResetTokenRepository.getPasswordResetTokenByToken(token);

        String response =  !isTokenFound(passToken) ? "Token inválido"
                : isTokenExpired(passToken) ? "Token expirado"
                : null;
        if(response != null)
            throw new MedLinkedException(response, HttpStatus.BAD_REQUEST);
        return response;
    }

    @Override
    public SimpleMailMessage constructResetTokenEmail(
            String contextPath, String token, Usuario usuario
    ) {
        String url = contextPath + "http://localhost:3000/senha/change?token=" + token;
        String message = "Clique no link para mudar sua senha";
        return constructEmail("Redefinição de Senha", message + " \r\n" + url, usuario);
    }

    @Override
    public SimpleMailMessage constructEmail(String subject, String body, Usuario usuario) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(usuario.getPessoa().getEmail());
        email.setFrom("support.email");
        return email;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        return passToken.getExpiryDate().after(new Date());
    }
}
