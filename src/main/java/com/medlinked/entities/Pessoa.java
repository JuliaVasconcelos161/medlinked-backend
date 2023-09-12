package com.medlinked.entities;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "TB_PESSOA")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPessoa;

    @Column(length = 120, nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, columnDefinition = "bigint")
    private BigInteger cpf;

    @Column(length = 120)
    private String email;

    @Column(nullable = false, columnDefinition = "bigint")
    private BigInteger celular;
}
