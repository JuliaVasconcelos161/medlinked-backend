package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class EmailException extends MedLinkedException {
    public EmailException() {
        super("Não foi possível enviar o email para confirmar essa ação, tente realizá-la novamente mais tarde.", HttpStatus.BAD_REQUEST);
    }
}
