package com.medlinked.repositories;

import com.medlinked.entities.Pessoa;

public interface PessoaRepository {
    Pessoa getOnePessoa(Integer idPessoa);

    Pessoa updatePessoa(Pessoa pessoa);
}
