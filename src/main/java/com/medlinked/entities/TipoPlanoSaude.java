package com.medlinked.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_TIPO_PLANO_SAUDE")
public class TipoPlanoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "smallint")
    private Integer idTipoPlanoSaude;

    @Column(nullable = false, length = 100)
    private String descricao;
}
