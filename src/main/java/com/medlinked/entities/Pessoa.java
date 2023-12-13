package com.medlinked.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_PESSOA")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPessoa;

    @Column(length = 120, nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private Long cpf;

    @Column(length = 120, unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Long celular;
}
