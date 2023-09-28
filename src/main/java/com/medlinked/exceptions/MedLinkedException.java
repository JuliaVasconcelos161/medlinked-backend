package com.medlinked.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MedLinkedException extends RuntimeException {

    private final HttpStatus httpStatus;

    public MedLinkedException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public MedLinkedException() {
        super("Algo deu errado, tente novamente mais tarde.");
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
