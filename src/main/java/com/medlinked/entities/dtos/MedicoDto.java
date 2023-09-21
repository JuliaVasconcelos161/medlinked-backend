package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class MedicoDto extends Pessoa {

    private Set<Integer> idsPlanosSaude;

    @NotNull
    private String ufCrm;

    @NotNull
    private Integer numeroCrm;

    @NotEmpty
    private Set<Integer> idsEspecialidades;
}
