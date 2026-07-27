package br.com.reservix.infrastructure.web.dtos.auth;

public record LoginRequest(
        String email, String password
) {
}
