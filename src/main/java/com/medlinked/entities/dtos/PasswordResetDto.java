package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasswordResetDto {
    private  String token;

    @NotBlank
    private String newPassword;
}
