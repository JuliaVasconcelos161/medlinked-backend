package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSenhaUsuarioDto {
    @NotBlank
    private String newPassword;

    @NotBlank
    private String oldPassword;
}
