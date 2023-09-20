package com.medlinked.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_PESSOA")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPessoa;

    @Column(length = 120, nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, columnDefinition = "bigint")
    private Long cpf;

    @Column(length = 120)
    private String email;

    @Column(nullable = false, columnDefinition = "bigint")
    private Long celular;
}
