package com.medlinked.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.medlinked.enums.TipoAgendamento;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_AGENDAMENTO")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgendamento;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataHoraInicioAgendamento;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataHoraFimAgendamento;

    @Column(length = 200)
    private String descricao;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TipoAgendamento tipoAgendamento;
    @ManyToOne
    @JoinColumn(name = "id_secretaria", nullable = false)
    private Secretaria secretaria;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_plano_saude", nullable = true)
    private PlanoSaude planoSaude;
}
