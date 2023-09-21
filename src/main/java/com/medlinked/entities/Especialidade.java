package com.medlinked.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "TB_ESPECIALIDADE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "smallint")
    private Integer idEspecialidade;

    @Column(nullable = false, length = 60, unique = true)
    private String descricao;

    @ManyToMany(mappedBy = "especialidades", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<CRM> medicosCrm;
}
