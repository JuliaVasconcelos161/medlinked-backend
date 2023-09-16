package com.medlinked.entities.dtos;

import com.medlinked.entities.PlanoSaude;
import lombok.Data;

import java.util.Set;

@Data
public class MedicoDto extends Pessoa {
    private Set<PlanoSaude> planosSaude;


}
