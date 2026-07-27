package br.com.reservix.infrastructure.web.dtos.auth;

import java.time.Instant;

public record LoginResponse(
        String acessToken, Instant expiresIn
) {
}
