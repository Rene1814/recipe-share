package com.rsolucoes.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

        private final SecretKey key = Keys.hmacShaKeyFor(
            JwtConstant.JWT_SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000))
        .claim("email", auth.getName())
        .signWith(key).compact();

        return jwt;
    }

    public String getEmailFromJwtToken(String jwt){
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jwt)
            .getBody()
            .get("email", String.class);
    }
    
}
