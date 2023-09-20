package com.medlinked.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "TB_MEDICO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Medico {
    @Id
    private Integer idMedico;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_medico")
    private Pessoa pessoa;

    @ManyToMany
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name = "TB_MEDICO_PLANO_SAUDE",
            joinColumns = @JoinColumn(name = "id_medico"),
            inverseJoinColumns = @JoinColumn(name = "id_plano_saude")
    )
    private Set<PlanoSaude> planosSaude;
}
