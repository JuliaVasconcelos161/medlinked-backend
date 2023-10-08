package com.medlinked.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class SecretariaDto extends UsuarioRegisterDto {
    @NotBlank
    protected String nome;

    @NotBlank
    @CPF
    protected String cpf;

    @Email
    protected String email;

    @NotNull
    protected Long celular;


}
