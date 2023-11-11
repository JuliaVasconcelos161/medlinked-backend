package com.medlinked.services.secretaria_service;

import com.medlinked.entities.Pessoa;
import com.medlinked.entities.Secretaria;
import com.medlinked.entities.dtos.SecretariaDto;
import com.medlinked.entities.dtos.SecretariaUsuarioDto;
import com.medlinked.entities.dtos.UsuarioResponseDto;
import com.medlinked.exceptions.ExistsVinculoMedicoSecretariaException;
import com.medlinked.repositories.secretaria_repository.SecretariaRepository;
import com.medlinked.services.pessoa_service.PessoaService;
import com.medlinked.services.usuario_service.UsuarioService;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;

@Service
public class SecretariaServiceImpl implements SecretariaService {

    private final PessoaService pessoaService;

    private final UsuarioService usuarioService;

    private final SecretariaRepository secretariaRepository;

    public SecretariaServiceImpl(PessoaService pessoaService, UsuarioService usuarioService, SecretariaRepository secretariaRepository) {
        this.pessoaService = pessoaService;
        this.usuarioService = usuarioService;
        this.secretariaRepository = secretariaRepository;
    }

    @Transactional
    @Override
    public UsuarioResponseDto createSecretaria(SecretariaUsuarioDto secretariaUsuarioDto) {
        pessoaService.validateNewPessoa(
                secretariaUsuarioDto.getCpf(), secretariaUsuarioDto.getEmail(), "Secretaria");
        Pessoa pessoa = pessoaService.returnPessoaByCpf(secretariaUsuarioDto.getCpf());
        Secretaria secretaria = Secretaria.builder()
                .pessoa(pessoa == null ?
                        pessoaService.createPessoa(secretariaUsuarioDto, "Secretaria") : pessoa)
                .build();
        secretaria = secretariaRepository.saveSecretaria(secretaria);
        UsuarioResponseDto usuarioResponseDto =
                usuarioService.register(secretariaUsuarioDto.getUsuarioRegisterDto(), secretaria.getPessoa());
        return usuarioResponseDto;
    }

    @Transactional
    @Override
    public Secretaria updateSecretaria(SecretariaDto secretariaDto, Integer idSecretaria) {
        Secretaria secretaria = secretariaRepository.getOneSecretaria(idSecretaria);
        secretaria.setPessoa(pessoaService.updatePessoa(idSecretaria, secretariaDto, "Secretaria"));
        return secretariaRepository.updateSecretaria(secretaria);
    }

    @Override
    public Secretaria getOneSecretaria(Integer idSecretaria) {
        return secretariaRepository.getOneSecretaria(idSecretaria);
    }

    @Transactional
    @Override
    public void deleteSecretaria(Integer idSecretaria) {
        Secretaria secretaria = secretariaRepository.getOneSecretaria(idSecretaria);
        if(BooleanUtils.isFalse(secretaria.getMedicos().isEmpty()))
            throw new ExistsVinculoMedicoSecretariaException();
        secretariaRepository.deleteSecretaria(secretaria);
        usuarioService.deleteUsuario(idSecretaria);
        if(pessoaService.existsPessoa(idSecretaria))
            pessoaService.deletePessoa(idSecretaria);
    }
}
