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
    private String dataInicioPreAgendamento;

    @NotNull
    private String dataFimPreAgendamento;

    @NotNull
    private String horaInicioGeracao;

    @NotNull
    private String horaFimGeracao;

    @NotNull
    private Integer tempoIntervalo;

    @NotNull
    private Boolean isApenasDiasUteis;
}
