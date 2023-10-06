package com.medlinked.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_ENDERECO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Endereco {
    @Id
    private Integer idPaciente;

    @Column(nullable = false)
    private Long cep;

    @Column(nullable = false, length = 100)
    private String logradouro;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 120)
    private String bairro;

    @Column(nullable = false, columnDefinition = "smallint")
    private Integer numero;

    @Column(length = 100)
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "uf", nullable = false)
    private Estado estado;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_paciente")
    @JsonIgnore
    private Paciente paciente;
}
