package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class NoObjectFoundException extends MedLinkedException {


    public NoObjectFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public NoObjectFoundException(String object){
        super(object + " não encontrado(a).", HttpStatus.NOT_FOUND);
    }
}
