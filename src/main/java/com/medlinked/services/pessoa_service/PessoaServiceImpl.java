package com.medlinked.services.pessoa_service;

import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.PessoaDto;
import com.medlinked.exceptions.ExistsException;
import com.medlinked.repositories.pessoa_repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    @Transactional
    public Pessoa updatePessoa(Integer idPessoa, PessoaDto pessoaDto, String especializacaoPessoa) {
        Pessoa pessoa = pessoaRepository.getOnePessoa(idPessoa);
        this.validatePessoaUpdate(pessoaDto.getCpf(), especializacaoPessoa, pessoaDto.getEmail(), idPessoa);
        this.validatePessoaUpdate(pessoaDto.getCpf(), "Pessoa", pessoaDto.getEmail(), idPessoa);
        pessoa.setCelular(pessoaDto.getCelular());
        pessoa.setCpf(Long.parseLong(pessoaDto.getCpf()));
        pessoa.setNome(pessoaDto.getNome());
        pessoa.setEmail(pessoaDto.getEmail());
        return pessoaRepository.updatePessoa(pessoa);
    }

    @Override
    @Transactional
    public Pessoa createPessoa(PessoaDto pessoaDto, String especializacaoPessoa) {
        this.validateNewPessoa(pessoaDto.getCpf(), pessoaDto.getEmail(), "Pessoa");
        Pessoa pessoa = Pessoa.builder()
                .nome(pessoaDto.getNome())
                .cpf(Long.parseLong(pessoaDto.getCpf()))
                .celular(pessoaDto.getCelular())
                .email(pessoaDto.getEmail())
                .build();
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa returnPessoaByCpf(String cpf) {
        return pessoaRepository.returnPessoaByCpf(cpf);
    }


    @Override
    public void validateNewPessoa(String cpfDto, String emailDto, String especializacaoPessoa) {
        if(pessoaRepository.existsEspecializacaoPessoaByCpf(cpfDto, especializacaoPessoa, null))
            throw new ExistsException(especializacaoPessoa, "CPF");
        if(pessoaRepository.existsEspecializacaoPessoaByEmail(emailDto, especializacaoPessoa, null))
            throw new ExistsException(especializacaoPessoa, "Email");
    }

    @Override
    public Pessoa getPessoaByCpf(Long cpf) {
        return pessoaRepository.returnPessoaByCpf(cpf.toString());
    }

    @Override
    public Pessoa getOnePessoa(Integer idPessoa) {
        return pessoaRepository.getOnePessoa(idPessoa);
    }

    @Override
    public boolean existsPessoa(Integer idPessoa) {
        return pessoaRepository.existsPessoa(idPessoa);
    }

    @Transactional
    @Override
    public void deletePessoa(Integer idPessoa) {
        Pessoa pessoa = pessoaRepository.getOnePessoa(idPessoa);
        pessoaRepository.deletePessoa(pessoa);
    }

    private void validatePessoaUpdate(String cpfDto, String especializacaoPessoa, String emailDto, Integer idPessoa) {
        if(pessoaRepository.existsEspecializacaoPessoaByCpf(cpfDto, especializacaoPessoa, idPessoa))
            throw new ExistsException(especializacaoPessoa, "CPF");
        if(pessoaRepository.existsEspecializacaoPessoaByEmail(emailDto, especializacaoPessoa, idPessoa))
            throw new ExistsException(especializacaoPessoa, "Email");
    }


}
