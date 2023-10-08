package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegisterDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;


}
