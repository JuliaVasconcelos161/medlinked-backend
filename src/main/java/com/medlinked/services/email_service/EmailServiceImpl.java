package com.medlinked.services.email_service;

import com.medlinked.entities.Agendamento;
import com.medlinked.entities.Pessoa;
import com.medlinked.entities.Usuario;
import com.medlinked.exceptions.MedLinkedException;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.medlinked.utils.JavaDateFormatter.EMAILFORMATTER;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendEmailResetPassword(String token, Usuario usuario) {
        javaMailSender.send(this.constructResetTokenEmail(token, usuario));
    }


    @Override
    public void sendEmailAgendamentoConfirmacao(Agendamento agendamento) {
        javaMailSender.send(this.constructEmailAgendamentoConfirmacao(agendamento));
    }

    @Override
    public MimeMessage constructEmail(String subject, String body, Pessoa pessoa) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        Multipart multipart = new MimeMultipart();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, "text/html; charset=UTF-8");
        multipart.addBodyPart(mimeBodyPart);
        mimeMessage.setContent(multipart);
        mimeMessage.setSubject(subject);
        mimeMessage.setRecipients(Message.RecipientType.TO, pessoa.getEmail());
        mimeMessage.setFrom("support.email");
        return mimeMessage;
    }

    public MimeMessage constructEmailAgendamentoConfirmacao(Agendamento agendamento) {
        StringBuilder message = new StringBuilder("<!DOCTYPE html>");
        message.append("<html lang=\"pt-br\" >");
        message.append("<head>");
        message.append("<title>MedLinked</title>");
        message.append("<meta name=\"viewport\" content=\"initial-scale=1.0, width=device-width\" />");
        message.append("</head>");
        message.append("<body>");
        message.append("<h1 style=\"color: black;\">Olá! ");
        message.append(agendamento.getPaciente().getPessoa().getNome());
        message.append(", tudo bem?</h1>");
        message.append("<br>");
        message.append("<h2 style=\"color: black;\">Estamos muito felizes em ter você conosco!!!</h2>");
        message.append("<br>");
        message.append("<p>Foi marcada uma consulta para você com ");
        message.append(agendamento.getMedico().getPessoa().getNome());
        message.append(" na data de ");
        message.append(agendamento.getDataHoraInicioAgendamento().format(EMAILFORMATTER));
        message.append(" </p>");
        message.append("</body>");
        message.append("</html>");
        try {
            return constructEmail("Consulta Marcada", message.toString() , agendamento.getPaciente().getPessoa());
        } catch (MessagingException e) {
            throw new MedLinkedException("Não foi possível enviar o email.", HttpStatus.BAD_REQUEST);
        }
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
            return constructEmail("Redefinição de Senha", message.toString() , usuario.getPessoa());
        } catch (MessagingException e) {
            throw new MedLinkedException("Não foi possível enviar o email.", HttpStatus.BAD_REQUEST);
        }
    }

}
