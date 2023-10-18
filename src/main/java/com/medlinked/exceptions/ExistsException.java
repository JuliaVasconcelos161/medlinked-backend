package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class ExistsException extends MedLinkedException {
    public ExistsException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public ExistsException(String object1, String object2){
        super("Já existe " + object1 + " com " + object2 + " informado.", HttpStatus.CONFLICT);
    }
}

