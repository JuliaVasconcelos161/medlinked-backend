package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotNull;

public class PlanoSaudePacienteDto {
    @NotNull
    private Long num_carteirinha;


    private Integer idTipoPlanoSaude;

}
