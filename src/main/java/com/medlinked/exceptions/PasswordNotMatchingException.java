package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class PasswordNotMatchingException extends MedLinkedException {
    public PasswordNotMatchingException() {
        super("Senha inserida não é igual a senha anteriormente definida no sistema.", HttpStatus.BAD_REQUEST);
    }
}
