package com.uit.tms.TaskManagement.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.uit.tms.TaskManagement.constants.Keywords;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {

    private final String secretKey;

    public static final int expirationMs = 86400000; // 1 day

    public JwtUtil(@Value("${spring.application.secret}") String secretKey) {
        this.secretKey = secretKey;
    }

    public String generateToken(String username) {
        SecretKey key = getSecretKey();
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            System.out.println("Invalid JWT: " + e.getMessage());
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        JwtParser parser = Jwts.parser().verifyWith(getSecretKey()).build();
        Claims claims = parser.parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String getTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;

        for (Cookie cookie : cookies) {
            if (Keywords.JWT_TOKEN.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }
}