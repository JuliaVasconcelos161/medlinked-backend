package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class ExistsDescricao extends MedLinkedException {
    public ExistsDescricao(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public ExistsDescricao(String object) {
        super("Já existe " + object + " com essa descrição.", HttpStatus.CONFLICT);
    }
}
