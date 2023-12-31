package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlanoSaudeDto {

    @NotBlank
    private String descricao;
}
