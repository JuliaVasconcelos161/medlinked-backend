package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class NoObjectFound extends MedLinkedException {


    public NoObjectFound(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public NoObjectFound(String object){
        super(object + " não encontrado(a).", HttpStatus.NOT_FOUND);
    }
}
