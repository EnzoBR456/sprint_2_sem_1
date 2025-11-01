package com.example.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Chave FIXA para desenvolvimento (32 bytes para HS256)
    private final Key key = Keys.hmacShaKeyFor("minhaChaveSecretaSuperSeguraParaSprint4Hete2024!!".getBytes());

    public String generateToken(String username) {
        System.out.println("🎫 Gerando token para: " + username);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("✅ Token gerado: " + token.substring(0, 20) + "...");
        return token;
    }

    public String validateToken(String token) {
        try {
            System.out.println("🔐 Validando token: " + token.substring(0, 20) + "...");

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            System.out.println("✅ Token VÁLIDO para usuário: " + username);
            return username;

        } catch (Exception e) {
            System.out.println("❌ ERRO na validação do token: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            return null;
        }
    }
}
