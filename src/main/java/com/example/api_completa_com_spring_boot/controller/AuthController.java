package com.example.api_completa_com_spring_boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_completa_com_spring_boot.model.Usuario;
import com.example.api_completa_com_spring_boot.security.JwtUtil;
import com.example.api_completa_com_spring_boot.service.UsuarioService;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        Usuario usuario = usuarioService.registrarUsuario(request.get("username"), "password");
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Usuario usuario = usuarioService.buscarPorUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Senha incorreta");
        }

        String token = JwtUtil.generateToken(usuario.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }

}