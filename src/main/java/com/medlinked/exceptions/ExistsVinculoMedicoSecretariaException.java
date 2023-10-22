package com.medlinked.exceptions;

import org.springframework.http.HttpStatus;

public class ExistsVinculoMedicoSecretariaException extends MedLinkedException {
    public ExistsVinculoMedicoSecretariaException(){
        super("Não é possível deletar secretária que ainda possua vinculo com médicos.", HttpStatus.BAD_REQUEST);
    }
}
