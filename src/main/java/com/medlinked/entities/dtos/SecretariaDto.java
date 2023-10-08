package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SecretariaDto extends PessoaDto {

    @NotNull
    private UsuarioRegisterDto usuarioRegisterDto;


}
