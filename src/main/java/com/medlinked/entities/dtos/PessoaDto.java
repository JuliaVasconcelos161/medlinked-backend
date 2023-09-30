package com.medlinked.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class PessoaDto {

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
