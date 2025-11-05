package dev.maheshbabu.authservice.helpers;

import dev.maheshbabu.authservice.Config;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JWTHandler {

    private final Config config;

    public JWTHandler(Config config) {
        this.config = config;
    }

    public String generateToken(String email, String role) {

        return Jwts.builder()
                          .header()
                          .keyId("aKeyId")
                          .and()
                          .subject(email)
                          .claim("role", role)
                          .signWith(config.jwtSecretKey()).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(config.jwtSecretKey()).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
