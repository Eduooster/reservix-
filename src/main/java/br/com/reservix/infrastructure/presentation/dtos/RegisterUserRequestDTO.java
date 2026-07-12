package br.com.reservix.infrastructure.presentation.dtos;

public record RegisterUserRequestDTO(
        String email, String password,String name
) {
}
