package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicoDto extends PessoaDto {

    @NotBlank
    private String ufCrm;

    @NotNull
    private Integer numeroCrm;

    @NotEmpty
    private Set<Integer> idsEspecialidades;
}
