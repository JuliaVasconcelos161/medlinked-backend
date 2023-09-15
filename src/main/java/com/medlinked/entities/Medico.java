package com.medlinked.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_MEDICO")
public class Medico {
    @Id
    private Integer idMedico;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_medico")
    private Pessoa pessoa;

}
