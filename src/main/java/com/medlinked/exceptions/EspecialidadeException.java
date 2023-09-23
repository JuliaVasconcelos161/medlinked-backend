package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class EspecialidadeException extends MedLinkedException {

    public EspecialidadeException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public EspecialidadeException() {
        super("O CRM de um médico pode estar vinculado a no máximo duas especialidades.", HttpStatus.CONFLICT);
    }
}
