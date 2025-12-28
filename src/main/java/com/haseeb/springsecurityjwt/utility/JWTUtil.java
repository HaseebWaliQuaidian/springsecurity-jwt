package com.haseeb.springsecurityjwt.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {

    private final String SECRET = "my-super-key-long-enough-for-encryption-12345-67890";
    private final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    final long EXPIRATION_TIME = 1000 * 60 * 60;
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsernameFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String username, UserDetails userDetails, String token) {
        return userDetails.getUsername().equals(username) && !isTokenExpired(token);
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private boolean isTokenExpired (String token){
        return extractClaims(token).getExpiration().before(new Date());
    }
}