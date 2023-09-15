package com.medlinked.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "TB_SECRETARIA")
public class Secretaria {
    @Id
    private Integer idSecretaria;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_secretaria")
    private Pessoa pessoa;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name = "TB_MEDICO_SECRETARIA",
            joinColumns = @JoinColumn(name = "id_secretaria"),
            inverseJoinColumns = @JoinColumn(name = "id_medico"))
    private Set<Medico> medicos;
}
