package com.medlinked.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "TB_PLANO_SAUDE_PACIENTE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PlanoSaudePaciente {
    @Embeddable
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
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


    @Column(nullable = false)
    private Long numeroCarteirinha;

    @ManyToOne
    @JoinColumn(name = "id_tipo_plano_saude", nullable = false)
    private TipoPlanoSaude tipoPlanoSaude;

}
