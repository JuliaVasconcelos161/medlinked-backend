package com.medlinked.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_ESTADO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estado {
    @Id
    @Column(length = 2)
    private String uf;

    @Column(nullable = false, length = 20, unique = true)
    private String descricao;
}
