package com.rsolucoes.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader(JwtConstant.JWT_HEADER);
        if (authorizationHeader != null && !authorizationHeader.isBlank()) {
            try {
                String jwt = authorizationHeader.startsWith("Bearer ")
                        ? authorizationHeader.substring(7)
                        : authorizationHeader;
                SecretKey key = Keys.hmacShaKeyFor(
                        JwtConstant.JWT_SECRET.getBytes(StandardCharsets.UTF_8));
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();
                String email = claims.get("email", String.class);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        email, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch(Exception e) {
                throw new BadCredentialsException("Invalid token", e);
            }
        }
        filterChain.doFilter(request, response);
    }


    
}
