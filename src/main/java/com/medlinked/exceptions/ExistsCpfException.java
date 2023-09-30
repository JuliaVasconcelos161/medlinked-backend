package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class ExistsCpfException extends MedLinkedException {
    public ExistsCpfException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public ExistsCpfException(String pessoa){
        super("Já existe " + pessoa +  " com esse cpf.", HttpStatus.CONFLICT);
    }
}
