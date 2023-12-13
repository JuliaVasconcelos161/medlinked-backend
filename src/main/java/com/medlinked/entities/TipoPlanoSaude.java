package com.medlinked.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_TIPO_PLANO_SAUDE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipoPlanoSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoPlanoSaude;

    @Column(nullable = false, length = 100, unique = true)
    private String descricao;
}
