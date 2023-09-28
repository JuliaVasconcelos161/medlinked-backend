package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlanoSaudeDto {

    @NotBlank
    private String descricao;
}
