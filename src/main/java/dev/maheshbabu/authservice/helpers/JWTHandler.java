package dev.maheshbabu.authservice.helpers;

import dev.maheshbabu.authservice.Config;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

@Component
public class JWTHandler {

    private final Config config;

    public JWTHandler(Config config) {
        this.config = config;
    }

    public String generateToken(String email, String role, long userId) {

        return Jwts.builder()
                          .header()
                          .keyId("aKeyId")
                          .and()
                          .subject(email)
                          .claim("role", role).claim("userId", userId)
                          .signWith(config.jwtSecretKey()).compact();
    }

    public boolean validateToken(String token, Long userId) {
        try {
            Jws<Claims> jws =  Jwts.parser().verifyWith(config.jwtSecretKey()).build().parseSignedClaims(token);

            if(jws.getPayload().get("userId", Long.class) .equals(userId)) {
                return true;
            }
        } catch (JwtException e) {
            return false;
        }

        return false;
    }
}
