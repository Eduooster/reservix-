package br.com.reservix.infrastructure.presentation.dtos;

public record LoginRequestDTO(
        String email, String password
) {
}
