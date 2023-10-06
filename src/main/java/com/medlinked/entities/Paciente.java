package com.medlinked.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_PACIENTE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Paciente{

    @Id
    private Integer idPaciente;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_paciente")
    private Pessoa pessoa;

}
