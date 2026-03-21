package com.project.shipment_tracking_system.Security;

import com.project.shipment_tracking_system.Enum.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtUtil {

    private static final String secretKey = "h352ljiouo5826283klsdhfh832onlm320215jnb5k3475hjekhfjkjeli4983589237";

    private Key getKey()
    {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(String username, Role role)
    {
        return
                 Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS256,getKey())
                .compact();
    }

    public String extractUsername(String token)
    {
        return Jwts.parserBuilder().setSigningKey(getKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }


    public boolean validateToken(String token, String username)
    {
        return username.equals(extractUsername(token)) &&
                new Date().before(Jwts.parserBuilder().setSigningKey(getKey()).build()
                        .parseClaimsJws(token).getBody().getExpiration());
    }

}
