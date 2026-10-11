package com.Store_api.store.Services;

import com.Store_api.store.Config.JwtConfig;
import com.Store_api.store.Enum.Role;
import com.Store_api.store.dto.Auth.JwtResponse;
import com.Store_api.store.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Data
public class JwtServices {
    private final JwtConfig jwtConfig;
    public String generateAccessToken(User user) {
        final long tokenExpiration=jwtConfig.getAccessTokenExpiration() ;
        return generateToken(user,tokenExpiration);
    }
    public String generateRefreshToken(User user) {
        final long tokenExpiration=jwtConfig.getRefreshTokenExpiration();
        return generateToken(user,tokenExpiration);
    }
    private String generateToken(User user,long expiration) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * expiration))
                .signWith(jwtConfig.getSecretKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            var claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        }
        catch (JwtException e) {
            return false;
        }
    }
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public Long getUserIdFromToken(String token) {
        return Long.valueOf(getClaims(token).getSubject());
    }
    public Role getRoleFromToken(String token) {
        return Role.valueOf(getClaims(token).get("role", String.class));
    }
}
