package com.medlinked.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "TB_PLANO_SAUDE_PACIENTE")
public class PlanoSaudePaciente {
    @Embeddable
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class PlanoSaudePacientePk implements Serializable {
        @ManyToOne
        @JoinColumn(name = "id_plano_saude")
        private PlanoSaude planoSaude;

        @ManyToOne
        @JoinColumn(name = "id_paciente")
        private Paciente paciente;

    }

    @EmbeddedId
    private PlanoSaudePacientePk idPlanoSaudePaciente;


    @Column(nullable = false, columnDefinition = "bigint")
    private Integer num_carteirinha;

    @ManyToOne
    @JoinColumn(name = "id_tipo_plano_saude")
    private TipoPlanoSaude tipoPlanoSaude;

}
