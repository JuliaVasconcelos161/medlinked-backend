package com.medlinked.services.email_service;

import com.medlinked.entities.Agendamento;
import com.medlinked.entities.Pessoa;
import com.medlinked.entities.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public interface EmailService {
    MimeMessage constructResetTokenEmail(Usuario usuario);

    MimeMessage constructEmail(String subject, String body, Pessoa pessoa) throws MessagingException;

    void sendEmailAgendamentoConfirmacao(Agendamento agendamento);

    void sendEmailResetPassword(Usuario usuario);
}
