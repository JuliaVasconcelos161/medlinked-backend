package com.medlinked.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_PESSOA")
@Data
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPessoa;

    @Column(length = 120, nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, columnDefinition = "bigint")
    private Integer cpf;

    @Column(length = 120)
    private String email;

    @Column(nullable = false, columnDefinition = "bigint")
    private Integer celular;
}
