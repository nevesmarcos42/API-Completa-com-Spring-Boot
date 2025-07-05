package com.example.api_completa_com_spring_boot.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.api_completa_com_spring_boot.model.Usuario;
import com.example.api_completa_com_spring_boot.repository.UsuarioRepository;

@Service
public class UsuarioDetailService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implementar a lógica para carregar o usuário do banco de dados ou outro
        // serviço
        // Exemplo:
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER") // Supondo que roles seja uma coleção de strings
                .build();

    }

}
