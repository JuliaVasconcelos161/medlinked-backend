package com.medlinked.entities.dtos;

import com.medlinked.enums.TipoAgendamento;
import jakarta.validation.constraints.NotNull;

public class PreAgendamentoDto {
    @NotNull
    private Integer idMedico;

    @NotNull
    private String dataHoraInicioPreAgendamento;

    @NotNull
    private String dataHoraFimPreAgendamento;

    @NotNull
    private Integer tempoIntervalo;

    @NotNull
    private TipoAgendamento tipoAgendamento;
}
