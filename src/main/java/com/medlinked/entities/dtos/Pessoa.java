package com.medlinked.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pessoa {

    @NotBlank
    protected String nome;

    @NotBlank
    @CPF
    protected Long cpf;

    @Email
    protected String email;

    @NotBlank
    protected Long celular;
}
