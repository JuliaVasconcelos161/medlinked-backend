package com.medlinked.services.pessoa_service;

import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.PessoaDto;

public interface PessoaService {
    Pessoa updatePessoa(Integer idPessoa, PessoaDto pessoaDto, String especializacaoPessoa);

    Pessoa createPessoa(PessoaDto pessoaDto, String especializacaoPessoa);

    Pessoa returnPessoaByCpf(String cpf);

    void validateNewPessoa(String cpfDto, String emailDto, String especializacaoPessoa);

    Pessoa getPessoaByCpf(Long cpf);

    Pessoa getOnePessoa(Integer idPessoa);

    boolean existsPessoa(Integer idPessoa);

    void deletePessoa(Integer idPessoa);
}
