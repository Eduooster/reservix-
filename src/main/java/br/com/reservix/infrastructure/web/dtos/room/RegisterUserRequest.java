package br.com.reservix.infrastructure.web.dtos.room;

public record RegisterUserRequest(
        String email, String password,String name
) {
}
