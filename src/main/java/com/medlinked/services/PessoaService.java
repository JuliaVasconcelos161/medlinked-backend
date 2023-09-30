package com.medlinked.services;

import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.PessoaDto;

public interface PessoaService {
    Pessoa updatePessoa(Integer idPessoa, PessoaDto pessoaDto);
}
