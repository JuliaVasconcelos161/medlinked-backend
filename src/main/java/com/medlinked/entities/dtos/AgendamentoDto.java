package com.medlinked.entities.dtos;

import com.medlinked.enums.TipoAgendamento;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgendamentoDto {

    @NotNull
    private Integer idMedico;

    private Integer idPlanoSaude;

    @NotNull
    private Integer idPaciente;

    @NotNull
    private String dataHoraInicioAgendamento;

    @NotNull
    private String dataHoraFimAgendamento;

    private String descricao;
    @NotNull
    private TipoAgendamento tipoAgendamento;
}
