package com.medlinked.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_ESTADO")
public class Estado {
    @Id
    @Column(length = 2)
    private Character uf;

    @Column(nullable = false, length = 20)
    private String descricao;
}
