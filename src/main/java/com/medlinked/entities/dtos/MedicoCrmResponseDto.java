package com.medlinked.entities.dtos;

import com.medlinked.entities.Especialidade;
import com.medlinked.entities.Estado;
import com.medlinked.entities.PlanoSaude;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicoCrmResponseDto {
    Integer idMedico;

    String nome;

    Long cpf;

    String email;

    Long celular;

    List<PlanoSaude> planosSaudeMedico;

    private Estado estado;

    private Integer numeroCrm;

    private List<Especialidade> especialidades;

    public MedicoCrmResponseDto(Integer idMedico, String nome, Long cpf, String email, Long celular,
                                Estado estado, Integer numeroCrm) {
        this.idMedico = idMedico;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.celular = celular;
        this.estado = estado;
        this.numeroCrm = numeroCrm;
    }
}
