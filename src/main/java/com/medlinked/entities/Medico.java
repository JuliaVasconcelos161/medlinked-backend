package com.medlinked.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TB_MEDICO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "planosSaude")
public class Medico {
    @Id
    private Integer idMedico;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_medico")
    private Pessoa pessoa;

    @ManyToMany
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @JoinTable(name = "TB_MEDICO_PLANO_SAUDE",
            joinColumns = @JoinColumn(name = "id_medico"),
            inverseJoinColumns = @JoinColumn(name = "id_plano_saude")
    )
    private Set<PlanoSaude> planosSaude;


    public void removePlanoSaude(PlanoSaude planoSaude) {
        this.planosSaude.remove(planoSaude);
        planoSaude.getMedicos().remove(this);
    }

    public void removeAllPlanosSaude(List<PlanoSaude> planosSaude) {
        this.planosSaude.removeAll(planosSaude);
        planosSaude.forEach(plano -> plano.getMedicos().remove(this));
    }
}
