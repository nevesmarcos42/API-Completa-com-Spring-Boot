package com.example.api_completa_com_spring_boot.security;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
    private static final Key key = io.jsonwebtoken.security.Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION_TIME = 86400000; // 1 dia em milissegundos

    public static String generateToken(String username) {
        return io.jsonwebtoken.Jwts.builder()
                .setSubject(username)
                .setExpiration(new java.util.Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public static String extractUsernameFromToken(String token) {
        return io.jsonwebtoken.Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            io.jsonwebtoken.Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            return false; // Token expirado
        } catch (io.jsonwebtoken.JwtException e) {
            return false; // Token inválido
        }
    }
}
