package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PessoaCpfDto {
    @NotNull
    private Long cpf;
}
