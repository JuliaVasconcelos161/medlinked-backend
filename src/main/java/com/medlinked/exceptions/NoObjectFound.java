package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class NoObjectFound extends MedLinkedException {


    public NoObjectFound(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public NoObjectFound(){
        super("Nenhum resultado encontrado.", HttpStatus.NOT_FOUND);
    }
}
