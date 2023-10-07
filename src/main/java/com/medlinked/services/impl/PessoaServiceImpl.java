package com.medlinked.services.impl;

import com.medlinked.entities.Pessoa;
import com.medlinked.entities.dtos.PessoaDto;
import com.medlinked.exceptions.ExistsException;
import com.medlinked.repositories.PessoaRepository;
import com.medlinked.services.PessoaService;
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
        this.validatePessoaUpdate(pessoa.getCpf(), pessoaDto.getCpf(), especializacaoPessoa,
                pessoa.getEmail(), pessoaDto.getEmail());
        this.validatePessoaUpdate(pessoa.getCpf(), pessoaDto.getCpf(), "Pessoa",
                pessoa.getEmail(), pessoaDto.getEmail());
        pessoa.setCelular(pessoaDto.getCelular());
        pessoa.setCpf(Long.parseLong(pessoaDto.getCpf()));
        pessoa.setNome(pessoaDto.getNome());
        pessoa.setEmail(pessoaDto.getEmail());
        return pessoaRepository.updatePessoa(pessoa);
    }

    @Override
    @Transactional
    public Pessoa createPessoa(PessoaDto pessoaDto, String especializacaoPessoa) {
        this.validateNewEspecializacaoPessoa(pessoaDto.getCpf(), pessoaDto.getEmail(), "Pessoa");
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
    public void validateNewEspecializacaoPessoa(String cpfDto, String emailDto, String especializacaoPessoa) {
        if(pessoaRepository.existsEspecializacaoPessoaByCpf(cpfDto, especializacaoPessoa))
            throw new ExistsException(especializacaoPessoa, "CPF");
        if(pessoaRepository.existsEspecializacaoPessoaByEmail(emailDto, especializacaoPessoa))
            throw new ExistsException(especializacaoPessoa, "Email");
    }

    private void validatePessoaUpdate(Long cpfPessoa, String cpfDto, String especializacaoPessoa,
                                      String emailPessoa, String emailDto) {
        this.validateCpfUpdatePessoa(cpfPessoa, cpfDto, especializacaoPessoa);
        this.validateEmailUpdatePessoa(emailPessoa, emailDto, especializacaoPessoa);
    }

    private void validateCpfUpdatePessoa(Long cpfPessoa, String cpfDto, String especializacaoPessoa) {
        boolean isPessoaCpfEqualsPessoaDtoCpf = cpfPessoa.equals(Long.parseLong(cpfDto));
        if(pessoaRepository.existsEspecializacaoPessoaByCpf(cpfDto, especializacaoPessoa) && !isPessoaCpfEqualsPessoaDtoCpf)
            throw new ExistsException(especializacaoPessoa, "CPF");
    }

    private void validateEmailUpdatePessoa(String emailPessoa, String emailDto, String especializacaoPessoa) {
        boolean isPessoaEmailEqualsPessoaDtoEmail = emailPessoa.equals(emailDto);
        if(pessoaRepository.existsEspecializacaoPessoaByEmail(emailDto, especializacaoPessoa) && !isPessoaEmailEqualsPessoaDtoEmail)
            throw new ExistsException(especializacaoPessoa, "Email");
    }

}
