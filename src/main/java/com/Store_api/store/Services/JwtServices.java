package com.Store_api.store.Services;

import com.Store_api.store.Config.JwtConfig;
import com.Store_api.store.Enum.Role;
import com.Store_api.store.dto.Auth.JwtResponse;
import com.Store_api.store.entities.User;
import io.jsonwebtoken.Claims;
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
    public Jwt generateAccessToken(User user) {
        final long tokenExpiration=jwtConfig.getAccessTokenExpiration() ;
        return generateToken(user,tokenExpiration);
    }
    public Jwt generateRefreshToken(User user) {
        final long tokenExpiration=jwtConfig.getRefreshTokenExpiration();
        return generateToken(user,tokenExpiration);
    }
    private Jwt generateToken(User user, long expiration) {
        var claims= Jwts.claims()
                .subject(user.getId().toString())
                .add("email", user.getEmail())
                .add("name", user.getName())
                .add("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * expiration))
                .build();

        return new Jwt(claims,jwtConfig.getSecretKey());
    }
    public Jwt parseToken(String token) {
        try {
            var claims = getClaims(token);
            return new Jwt(claims,jwtConfig.getSecretKey());
        }
        catch (JwtException e) {
            return null;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
