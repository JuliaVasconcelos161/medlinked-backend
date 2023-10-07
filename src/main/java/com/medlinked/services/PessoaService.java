package com.medlinked.services;

import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.PessoaDto;

public interface PessoaService {
    Pessoa updatePessoa(Integer idPessoa, PessoaDto pessoaDto, String especializacaoPessoa);

    Pessoa createPessoa(PessoaDto pessoaDto, String especializacaoPessoa);

    Pessoa returnPessoaByCpf(String cpf);

    void validateNewEspecializacaoPessoa(String cpfDto, String emailDto, String especializacaoPessoa);

    Pessoa getPessoaByCpf(Long cpf);
}
