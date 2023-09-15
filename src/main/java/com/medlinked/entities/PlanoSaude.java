package com.medlinked.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_PLANO_SAUDE")
public class PlanoSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "smallint")
    private Integer idPlanoSaude;

    @Column(nullable = false, length = 130)
    private String descricao;
}
