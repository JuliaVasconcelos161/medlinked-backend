package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class ExistsCpf extends MedLinkedException {
    public ExistsCpf(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public ExistsCpf(String pessoa){
        super("Já existe " + pessoa +  " com esse cpf.", HttpStatus.CONFLICT);
    }
}
