package com.zion.api.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "minha_chave_secreta"; // Troque por uma chave segura
    private static final long EXPIRATION_TIME = 3600000; // 1 hora em milissegundos

    public String generateToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public String getEmailFromToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public boolean isValidToken(String token) {
        return getEmailFromToken(token) != null;
    }
}
