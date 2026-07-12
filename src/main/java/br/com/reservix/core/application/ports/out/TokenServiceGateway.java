package br.com.reservix.core.application.ports.out;

import java.time.Instant;

public interface TokenServiceGateway {
    String generateToken(Long userId);
    String getSubject(String token);
    Instant expiryTime();
}
