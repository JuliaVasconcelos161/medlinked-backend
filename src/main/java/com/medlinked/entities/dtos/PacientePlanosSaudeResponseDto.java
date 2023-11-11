package com.medlinked.entities.dtos;

import com.medlinked.entities.Paciente;
import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PacientePlanosSaudeResponseDto {
    Paciente paciente;

    List<PlanoSaudePacienteResponseDto> planosSaudePaciente;

}
