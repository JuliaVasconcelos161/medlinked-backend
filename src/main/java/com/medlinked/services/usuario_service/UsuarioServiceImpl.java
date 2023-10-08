package com.medlinked.services.usuario_service;

import com.medlinked.entities.Pessoa;
import com.medlinked.entities.Usuario;
import com.medlinked.entities.dtos.UsuarioRegisterDto;
import com.medlinked.entities.dtos.UsuarioResponseDto;
import com.medlinked.exceptions.ExistsException;
import com.medlinked.repositories.usuario_repository.UsuarioRepository;
import com.medlinked.services.jwt_service.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(JwtService jwtService, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
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
        String jwtToken = jwtService.generateToken(mapExtraClaims, usuario);
        return new UsuarioResponseDto(jwtToken);
    }

}
