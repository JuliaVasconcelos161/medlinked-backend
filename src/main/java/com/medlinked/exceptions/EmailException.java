package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class EmailException extends MedLinkedException {
    public EmailException() {
        super("Não foi possível enviar o email para resetar senha, tente novamente mais tarde.", HttpStatus.BAD_REQUEST);
    }
}
