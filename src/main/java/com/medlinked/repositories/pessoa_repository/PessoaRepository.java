package com.medlinked.repositories.pessoa_repository;

import com.medlinked.entities.Pessoa;

public interface PessoaRepository {
    Pessoa getOnePessoa(Integer idPessoa);

    Pessoa updatePessoa(Pessoa pessoa);

    Pessoa save(Pessoa pessoa);

    boolean existsEspecializacaoPessoaByCpf(String cpf, String especializacaoPessoa);

    boolean existsEspecializacaoPessoaByEmail(String email, String especializacaoPessoa);

    Pessoa returnPessoaByCpf(String cpf);
}
