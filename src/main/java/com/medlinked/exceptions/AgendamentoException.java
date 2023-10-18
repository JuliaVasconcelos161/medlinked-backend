package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class AgendamentoException extends MedLinkedException {
    public AgendamentoException() {
        super("Não é possível marcar um horário cujo início seja depois do fim.", HttpStatus.BAD_REQUEST);
    }
}
