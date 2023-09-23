package com.medlinked.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_TIPO_PLANO_SAUDE")
@Data
public class TipoPlanoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "smallint")
    private Integer idTipoPlanoSaude;

    @Column(nullable = false, length = 100, unique = true)
    private String descricao;
}
