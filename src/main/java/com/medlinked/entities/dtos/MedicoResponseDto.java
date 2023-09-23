package com.medlinked.entities.dtos;

import com.medlinked.entities.CRM;
import com.medlinked.entities.Especialidade;
import com.medlinked.entities.PlanoSaude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicoResponseDto extends Pessoa {

    private Integer idMedico;

    private List<PlanoSaude> planosSaude;

    private CRM crm;

    public MedicoResponseDto(Integer idMedico, String nome, Long cpf, String email, Long celular) {
        this.idMedico = idMedico;
        this.nome = nome;
        this.cpf = this.convertCpf(cpf);
        this.email = email;
        this.celular = celular;
    }

    private String convertCpf(Long cpf) {
        StringBuilder cpfStringBuilder = new StringBuilder(cpf.toString());
        if(cpfStringBuilder.length() != 11) {
            int diferenca = 11 - cpfStringBuilder.length();
            for(int i = 0; i < diferenca; i++)
                cpfStringBuilder.insert(0, "0");
        }
        return cpfStringBuilder.toString();
    }
}
