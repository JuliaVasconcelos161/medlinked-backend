package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicoDto extends Pessoa {

    @NotNull
    private String ufCrm;

    @NotNull
    private Integer numeroCrm;

    @NotEmpty
    private Set<Integer> idsEspecialidades;
}
