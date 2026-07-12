package br.com.reservix.infrastructure.presentation.dtos;

import java.time.Instant;

public record LoginResponseDTO(
        String acessToken, Instant expiresIn
) {
}
