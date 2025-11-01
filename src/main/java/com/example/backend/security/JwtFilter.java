package com.example.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestPath = request.getRequestURI();
        System.out.println("\n🔒 JwtFilter interceptando: " + requestPath + " | Método: " + request.getMethod());

        // ✅ Ignora requisições do tipo OPTIONS (usadas pelo CORS)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        // ✅ Permite acesso aos endpoints públicos
        if (requestPath.startsWith("/auth/")) {
            System.out.println("✅ Rota pública, permitindo acesso...");
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        System.out.println("📨 Authorization header: " + (header != null ? header.substring(0, Math.min(header.length(), 50)) + "..." : "NULL"));

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            System.out.println("🔑 Token extraído (primeiros 20 chars): " + token.substring(0, Math.min(token.length(), 20)) + "...");

            String username = jwtUtil.validateToken(token);

            if (username == null) {
                System.out.println("❌ Token INVÁLIDO - Retornando 401");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido ou expirado");
                return;
            }

            System.out.println("✅ Token VÁLIDO para usuário: " + username + " - Permitindo acesso...");
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } else {
            System.out.println("❌ Token NÃO ENCONTRADO no header - Retornando 401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token de autorização não fornecido");
            return;
        }

        chain.doFilter(request, response);
    }
}
