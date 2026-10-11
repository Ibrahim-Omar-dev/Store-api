package com.Store_api.store.Services;

import com.Store_api.store.Enum.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.crypto.SecretKey;
import java.util.Date;
@Data
@AllArgsConstructor
public class Jwt {
    private Claims claims;
    private SecretKey  secretKey;

    public boolean isExpired()
    {
        return claims.getExpiration().before(new Date());
    }
    public Long getUserId()
    {
        return Long.valueOf(claims.getSubject());
    }
    public Role getRole()
    {
        return Role.valueOf(claims.get("role", String.class));
    }
    public  String toString()
    {
       return Jwts.builder().claims(claims).signWith(secretKey).compact();
    }
}
