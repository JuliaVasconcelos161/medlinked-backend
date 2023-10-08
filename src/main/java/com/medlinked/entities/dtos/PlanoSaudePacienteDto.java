package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlanoSaudePacienteDto {
    @NotNull
    private Long numeroCarteirinha;


    private Integer idTipoPlanoSaude;

}
