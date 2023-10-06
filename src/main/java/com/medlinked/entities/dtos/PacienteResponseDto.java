package com.medlinked.entities.dtos;

import com.medlinked.entities.Endereco;
import com.medlinked.entities.Paciente;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PacienteResponseDto {
    private Paciente paciente;

    private Endereco endereco;
}
