package com.medlinked.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "TB_CRM")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CRM {
    @Id
    @JsonIgnore
    private Integer idMedico;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_medico")
    @JsonIgnore
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "uf", nullable = false)
    private Estado estado;

    @Column(unique = true, nullable = false)
    private Integer numeroCrm;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "TB_ESPECIALIDADE_CRM",
            joinColumns = @JoinColumn(name = "id_medico"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidade"))
    private Set<Especialidade> especialidades;

    public CRM(Estado estado, Integer numeroCrm) {
        this.estado = estado;
        this.numeroCrm = numeroCrm;
    }
}
