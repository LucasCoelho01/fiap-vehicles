package com.vehicles.security;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("hackathon")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (Exception exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }
}

