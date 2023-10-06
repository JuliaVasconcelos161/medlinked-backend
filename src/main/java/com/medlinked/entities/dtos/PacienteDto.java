package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PacienteDto extends PessoaDto {
    @NotNull
   private EnderecoDto enderecoDto;

}
