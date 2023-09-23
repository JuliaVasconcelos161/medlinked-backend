package com.medlinked.entities.dtos;

import com.medlinked.entities.Especialidade;
import com.medlinked.entities.PlanoSaude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class MedicoResponseDto {

    private Integer idMedico;

    private List<PlanoSaude> planosSaude;

    private String ufCrm;

    private Integer numeroCrm;

    private List<Especialidade> especialidades;

    public MedicoResponseDto(Integer idMedico, String ufCrm, Integer numeroCrm){
        this.idMedico = idMedico;
        this.ufCrm = ufCrm;
        this.numeroCrm = numeroCrm;
    }

}
