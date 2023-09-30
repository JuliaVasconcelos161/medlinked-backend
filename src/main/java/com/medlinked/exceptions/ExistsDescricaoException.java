package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class ExistsDescricaoException extends MedLinkedException {
    public ExistsDescricaoException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public ExistsDescricaoException(String object) {
        super("Já existe " + object + " com essa descrição.", HttpStatus.CONFLICT);
    }
}
