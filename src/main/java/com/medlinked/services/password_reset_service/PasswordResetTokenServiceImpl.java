package com.medlinked.services.password_reset_service;

import com.medlinked.entities.PasswordResetToken;
import com.medlinked.entities.Usuario;
import com.medlinked.entities.dtos.PasswordResetDto;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.repositories.password_reset_repository.PasswordResetTokenRepository;
import com.medlinked.repositories.usuario_repository.UsuarioRepository;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
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
        PasswordResetToken passwordResetToken = passwordResetTokenRepository
                .returnPasswordResetTokenByUsuario(usuario.getUsername());
        if(passwordResetToken == null)
            this.createPasswordResetTokenForUsuario(usuario, token);
        else
            this.updatePasswordResetTokenForUsuario(passwordResetToken, token);
        javaMailSender.send(constructResetTokenEmail(token, usuario));
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
        String response =  !isTokenFound(passToken) ? "Token inválido"
                : isTokenExpired(passToken) ? "Token expirado"
                : null;
        if(response != null)
            throw new MedLinkedException(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public MimeMessage constructResetTokenEmail(String token, Usuario usuario) {
        StringBuilder message = new StringBuilder("<!DOCTYPE html>");
        message.append("<html lang=\"pt-br\" >");
        message.append("<head>");
        message.append("<title>MedLinked</title>");
        message.append("<meta name=\"viewport\" content=\"initial-scale=1.0, width=device-width\" />");
        message.append("</head>");
        message.append("<body>");
        message.append("<p>Clique no link para atualizar sua senha:<a href=\"http://localhost:3000/senha/change?token=\"");
        message.append(token);
        message.append(">Atualizar senha</a> </p>");
        message.append("</body>");
        message.append("</html>");
        try {
            return constructEmail("Redefinição de Senha", message.toString() , usuario);
        } catch (MessagingException e) {
            throw new MedLinkedException("Não foi possível enviar o email.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public MimeMessage constructEmail(String subject, String body, Usuario usuario) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        Multipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, "text/html; charset=UTF-8");
        multipart.addBodyPart(mimeBodyPart);
        mimeMessage.setContent(multipart);
        mimeMessage.setSubject(subject);
        mimeMessage.setRecipients(Message.RecipientType.TO, usuario.getPessoa().getEmail());
        mimeMessage.setFrom("support.email");
        return mimeMessage;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        return passToken.getExpiryDate().after(new Date());
    }

    private PasswordResetToken createPasswordResetTokenForUsuario(Usuario usuario, String token) {
        return passwordResetTokenRepository.savePasswordResetToken(new PasswordResetToken(usuario, token));
    }

    private PasswordResetToken updatePasswordResetTokenForUsuario(PasswordResetToken passwordResetToken, String token) {
        passwordResetToken.setToken(token);
        passwordResetToken.setExpiryDate(new Date());
        return passwordResetTokenRepository.updatePasswordResetToken(passwordResetToken);
    }
}
