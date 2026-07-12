package br.com.reservix.infrastructure.security;

import br.com.reservix.core.application.ports.out.TokenServiceGateway;
import br.com.reservix.infrastructure.persistence.repositories.JpaUserRepositoryAdapter;
import com.auth0.jwt.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
@RequiredArgsConstructor
public class TokenServiceAdapter implements TokenServiceGateway {
    @Value(value = "${api.security.token.secret}")
    private String secret;

    private final JpaUserRepositoryAdapter jpaUserRepositoryAdapter;



    @Override
    public String generateToken(Long idUsuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Reservix")
                    .withSubject(idUsuario.toString())
                    .withExpiresAt(expiryTime())
                    .sign(algoritmo);
        } catch (Exception exception) {
            throw new RuntimeException("erro ao gerrar token jwt", exception);
        }
    }

    @Override
    public String getSubject(String token) {
        try {

            DecodedJWT decoded = JWT.decode(token);
            String subject = decoded.getSubject();

            // 2. Valida somente assinatura (não expiração)
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algoritmo)
                    .withIssuer("API Reservix")
                    .build();

            try {
                verifier.verify(token);
            } catch (TokenExpiredException e) {

            }

            return subject;

        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido!");
        }
    }

    @Override
    public Instant expiryTime() {

        return LocalDateTime.now().plusHours(20).toInstant(ZoneOffset.of("-03:00"));
    }
}
