package com.medlinked.entities.dtos;

import com.medlinked.entities.PlanoSaude;
import com.medlinked.entities.TipoPlanoSaude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlanoSaudePacienteResponseDto {
    private PlanoSaude planoSaude;

    private Long numeroCarteirinha;

    private TipoPlanoSaude TipoPlanoSaude;

}
