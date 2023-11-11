package com.medlinked.services.usuario_service;

import com.medlinked.entities.Pessoa;
import com.medlinked.entities.Usuario;
import com.medlinked.entities.dtos.UpdateSenhaUsuarioDto;
import com.medlinked.entities.dtos.UsuarioRegisterDto;
import com.medlinked.entities.dtos.UsuarioResponseDto;
import com.medlinked.exceptions.ExistsException;
import com.medlinked.exceptions.MedLinkedException;
import com.medlinked.exceptions.PasswordNotMatchingException;
import com.medlinked.repositories.usuario_repository.UsuarioRepository;
import com.medlinked.services.jwt_service.JwtService;
import com.medlinked.services.pessoa_service.PessoaService;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final PessoaService pessoaService;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(PessoaService pessoaService,
                              JwtService jwtService,
                              PasswordEncoder passwordEncoder,
                              AuthenticationManager authenticationManager,
                              UsuarioRepository usuarioRepository) {
        this.pessoaService = pessoaService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    @Override
    public UsuarioResponseDto register(UsuarioRegisterDto usuarioRegisterDto, Pessoa pessoa) {
        boolean existsUsuario = usuarioRepository.existsByUsername(usuarioRegisterDto.getUsername());
        if(existsUsuario)
            throw new ExistsException("Usuário", "Username");
        Usuario usuario = Usuario.builder()
                .idUsuario(pessoa.getIdPessoa())
                .pessoa(pessoa)
                .username(usuarioRegisterDto.getUsername())
                .password(passwordEncoder.encode(usuarioRegisterDto.getPassword()))
                .build();
        usuarioRepository.saveUsuario(usuario);
        Map<String, Object> mapExtraClaims = new HashMap<>();
        mapExtraClaims.put("idUsuario", usuario.getIdUsuario());
        mapExtraClaims.put("nome", pessoa.getNome());
        String jwtToken = jwtService.generateToken(mapExtraClaims, usuario);
        return new UsuarioResponseDto(jwtToken);
    }

    @Transactional
    @Override
    public UsuarioResponseDto authenticate(UsuarioRegisterDto usuarioRegisterDto) throws MedLinkedException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuarioRegisterDto.getUsername(),
                        usuarioRegisterDto.getPassword()
                )
        );
        Usuario usuario = usuarioRepository.returnUsuarioByUsername(usuarioRegisterDto.getUsername());
        Map<String,Object> mapExtraClaims = new HashMap<>();
        Pessoa pessoa = pessoaService.getOnePessoa(usuario.getIdUsuario());
        mapExtraClaims.put("idUsuario", pessoa.getIdPessoa());
        mapExtraClaims.put("nome", pessoa.getNome());
        String jwtToken = jwtService.generateToken(mapExtraClaims, usuario);
        return new UsuarioResponseDto(jwtToken);
    }

    @Transactional
    @Override
    public Usuario updateSenhaUsuario(UpdateSenhaUsuarioDto updateSenhaUsuarioDto, Integer idUsuario) {
        Usuario usuario = usuarioRepository.getOneUsuario(idUsuario);
        if(BooleanUtils.isFalse(passwordEncoder.matches(updateSenhaUsuarioDto.getOldPassword(), usuario.getPassword())))
            throw new PasswordNotMatchingException();
        usuario.setPassword(passwordEncoder.encode(updateSenhaUsuarioDto.getNewPassword()));
        return usuarioRepository.updateUsuario(usuario);
    }
    @Transactional
    @Override
    public void deleteUsuario(Integer idUsuario) {
        Usuario usuario = usuarioRepository.getOneUsuario(idUsuario);
        usuarioRepository.deleteUsuario(usuario);
    }

}
