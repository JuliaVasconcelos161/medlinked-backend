package com.medlinked.entities.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgendamentoAutomaticoDto {
    @NotNull
    private Integer idMedico;

    @NotNull
    private String dataInicioAgendamentoAutomatico;

    @NotNull
    private String dataFimAgendamentoAutomatico;

    @NotNull
    private String horaInicioGeracao;

    @NotNull
    private String horaFimGeracao;

    @NotNull
    private Integer tempoIntervalo;

    @NotNull
    private Boolean isApenasSegundaASexta;
}
