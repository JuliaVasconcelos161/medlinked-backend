package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class ExistsException extends MedLinkedException {
    public ExistsException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public ExistsException(String especializacaoPessoa, String object){
        super("Já existe " + especializacaoPessoa + " com " + object + " informado.", HttpStatus.CONFLICT);
    }
}
