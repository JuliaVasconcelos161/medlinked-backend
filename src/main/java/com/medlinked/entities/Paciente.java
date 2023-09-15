package com.medlinked.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_PACIENTE")
public class Paciente{

    @Id
    private Integer idPaciente;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_paciente")
    private Pessoa pessoa;

}
