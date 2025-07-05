package com.example.api_completa_com_spring_boot.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.api_completa_com_spring_boot.model.Usuario;
import com.example.api_completa_com_spring_boot.repository.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario registrarUsuario(String username, String password) {
        String senhaCriptografada = passwordEncoder.encode(password);
        Usuario usuario = new Usuario(username, senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

}
