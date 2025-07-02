package org.example.tutorias11.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JWTUtil {

    // 🔒 Clave secreta robusta (¡debe tener al menos 32 caracteres!)
    private final String SECRET_KEY = "clave-secreta-segura-de-al-menos-32-caracteres";

    //  Tiempo de expiración del token (1 hora)
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    // 🔑 Genera la clave de firma para el algoritmo HS256
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // 📦 Genera un JWT con username como subject
    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles) // Aquí agregas los roles como claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Valida la estructura y firma del token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            // Puedes registrar el error para depuración:
            // System.out.println("Token inválido: " + e.getMessage());
            return false;
        }
    }

    // Extrae el "subject" del token (es decir, el username)
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
