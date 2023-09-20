package com.medlinked.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Pessoa {

    @NotBlank
    protected String nome;

    @NotNull
    protected Long cpf;

    @Email
    protected String email;

    @NotNull
    protected Long celular;
}
