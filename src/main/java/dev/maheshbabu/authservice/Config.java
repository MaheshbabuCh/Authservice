package dev.maheshbabu.authservice;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
public class Config {

    @Value("${jwt.secret}")
    private String jwtSecret;

   @Bean
    public SecretKey jwtSecretKey() {
        // jwtSecret must be at least 32 characters for HS256
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
