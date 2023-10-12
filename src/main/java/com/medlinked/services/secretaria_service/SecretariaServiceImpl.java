package com.medlinked.services.secretaria_service;

import com.medlinked.entities.Pessoa;
import com.medlinked.entities.Secretaria;
import com.medlinked.entities.dtos.SecretariaDto;
import com.medlinked.entities.dtos.SecretariaUpdateDto;
import com.medlinked.entities.dtos.UsuarioResponseDto;
import com.medlinked.repositories.secretaria_repository.SecretariaRepository;
import com.medlinked.services.pessoa_service.PessoaService;
import com.medlinked.services.usuario_service.UsuarioService;
import jakarta.transaction.Transactional;
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
    public UsuarioResponseDto createSecretaria(SecretariaDto secretariaDto) {
        pessoaService.validateNewEspecializacaoPessoa(
                secretariaDto.getCpf(), secretariaDto.getEmail(), "Secretaria");
        Pessoa pessoa = pessoaService.returnPessoaByCpf(secretariaDto.getCpf());
        Secretaria secretaria = Secretaria.builder()
                .pessoa(pessoa == null ?
                        pessoaService.createPessoa(secretariaDto, "Secretaria") : pessoa)
                .build();
        secretaria = secretariaRepository.saveSecretaria(secretaria);
        UsuarioResponseDto usuarioResponseDto =
                usuarioService.register(secretariaDto.getUsuarioRegisterDto(), secretaria.getPessoa());
        return usuarioResponseDto;
    }

    @Transactional
    @Override
    public Secretaria updateSecretaria(SecretariaUpdateDto secretariaUpdateDto, Integer idSecretaria) {
        Secretaria secretaria = secretariaRepository.getOneSecretaria(idSecretaria);
        secretaria.setPessoa(pessoaService.updatePessoa(idSecretaria, secretariaUpdateDto, "Secretaria"));
        return secretariaRepository.updateSecretaria(secretaria);
    }
}
