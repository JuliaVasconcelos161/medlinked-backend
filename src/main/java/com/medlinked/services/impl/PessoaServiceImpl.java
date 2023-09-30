package com.medlinked.services.impl;

import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.PessoaDto;
import com.medlinked.repositories.PessoaRepository;
import com.medlinked.services.PessoaService;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Pessoa updatePessoa(Integer idPessoa, PessoaDto pessoaDto) {
        Pessoa pessoa = pessoaRepository.getOnePessoa(idPessoa);
        pessoa.setCelular(pessoaDto.getCelular());
        pessoa.setCpf(Long.parseLong(pessoaDto.getCpf()));
        pessoa.setNome(pessoaDto.getNome());
        pessoa.setEmail(pessoaDto.getEmail());
        return pessoaRepository.updatePessoa(pessoa);
    }
}
