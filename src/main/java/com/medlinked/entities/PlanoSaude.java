package com.medlinked.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "TB_PLANO_SAUDE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanoSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "smallint")
    private Integer idPlanoSaude;

    @Column(nullable = false, length = 130, unique = true)
    private String descricao;

    @ManyToMany(mappedBy = "planosSaude", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Medico> medicos;

}
