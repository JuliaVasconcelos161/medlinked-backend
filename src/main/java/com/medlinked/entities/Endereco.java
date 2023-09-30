package com.medlinked.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_ENDERECO")
public class Endereco {
    @Id
    private Integer idPaciente;

    @Column(nullable = false)
    private Long cep;

    @Column(nullable = false, length = 100)
    private String logradouro;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, columnDefinition = "smallint")
    private Integer numero;

    @Column(length = 100)
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "uf", nullable = false)
    private Estado estado;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
}
