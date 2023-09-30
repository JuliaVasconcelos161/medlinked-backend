package com.medlinked.entities.dtos;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicoPlanoSaudeDto {

    private List<Integer> idsPlanosSaude;
}
